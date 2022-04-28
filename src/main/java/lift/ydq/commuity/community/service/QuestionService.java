package lift.ydq.commuity.community.service;

import lift.ydq.commuity.community.cacha.QuestionCache;
import lift.ydq.commuity.community.dto.PaginationDTO;
import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.dto.QuestionQueryDTO;
import lift.ydq.commuity.community.enums.SortEnum;
import lift.ydq.commuity.community.exception.CustomizeErrorCode;
import lift.ydq.commuity.community.exception.CustomizeException;
import lift.ydq.commuity.community.mapper.*;
import lift.ydq.commuity.community.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YDQ
 * @create 2022-04-02 15:07
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionCache questionCache;

    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private FollowMapper followMapper;


    public PaginationDTO list(String search, String tag, String sort, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search , ' ');
            search = Arrays
                    .stream(tags)
                    .filter(StringUtils::isNotBlank)
                    .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining("|"));
        }


        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        if (StringUtils.isNotBlank(tag)) {
            tag = tag.replace("+", "").replace("*", "").replace("?", "");
            questionQueryDTO.setTag(tag);
        }

        for (SortEnum sortEnum : SortEnum.values()) {
            if (sortEnum.name().toLowerCase().equals(sort)) {
                questionQueryDTO.setSort(sort);

                if (sortEnum == SortEnum.HOT7) {
                    questionQueryDTO.setTime(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 7);
                }
                if (sortEnum == SortEnum.HOT30) {
                    questionQueryDTO.setTime(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30);
                }
                break;
            }
        }
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);
        paginationDTO.setPagination(totalCount, page, size);
        if(page < 1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //5*(i-1)
        //Integer offset = size*(page-1);
        Integer offset = page < 1 ? 0 : size * (page - 1);
        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions){
            User user =userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO =new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setDescription("");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        List<QuestionDTO> stickies = questionCache.getStickies();
        if (stickies != null && stickies.size() != 0) {
            questionDTOList.addAll(0, stickies);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        paginationDTO.setPagination(totalCount, page, size);
        if(page < 1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //5*(i-1)
        Integer offset = size*(page-1);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        return getPaginationDTO(paginationDTO, questions);
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user =userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }


    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else {
            //更新
            question.setGmtModified(System.currentTimeMillis());

            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                            .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion,questionExample);
            if (updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())){
            return  new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ',');
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

    public void delete(Long id){
        questionMapper.deleteByPrimaryKey(id);
    }

    public PaginationDTO list(Long userId) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExample(example);
        return getPaginationDTO(paginationDTO, questions);

    }

    public List<String> listTag(Long userId){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions= questionMapper.selectByExample(questionExample);
        List<String> tags = new ArrayList<>();
        for (Question question : questions) {
             String[] questionTags = StringUtils.split(question.getTag(),",");
            for (String questionTag : questionTags) {
                if (islike(questionTag,tags)){
                    tags.add(questionTag);
                }
            }
        }
        return tags;
    }

    private boolean islike(String questionTag, List<String> questionTags) {
        for (String tag : questionTags) {
            if (tag.equals(questionTag) ){
                return false;
            }
        }
        return true;
    }

    @NotNull
    private PaginationDTO getPaginationDTO(PaginationDTO paginationDTO, List<Question> questions) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user =userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO =new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            String[] tags = StringUtils.split(question.getTag(), ',');
            String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
            questionDTO.setTag(regexpTag);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO folowList(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        FollowExample followExample = new FollowExample();
        followExample.createCriteria()
                .andRequesterEqualTo(userId)
                .andTypeEqualTo(2);
        List<Follow>followList = followMapper.selectByExample(followExample);
        List<Question> questionList = new ArrayList<>();
        Integer totalCount = (int)followMapper.countByExample(followExample);
        paginationDTO.setPagination(totalCount, page, size);
        if(page < 1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //5*(i-1)
        Integer offset = size*(page-1);
        for (Follow follow : followList) {
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(follow.getFollowers());
            List<Question> questions = questionMapper.selectByExample(questionExample);
            for (Question question : questions) {
                questionList.add(question);
            }
        }
        return getPaginationDTO(paginationDTO, questionList);

    }

    public PaginationDTO userList(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        FollowExample followExample = new FollowExample();
        followExample.createCriteria()
                .andRequesterEqualTo(userId)
                .andTypeEqualTo(1);
        List<Follow>followList = followMapper.selectByExample(followExample);
        List<User> userList = new ArrayList<>();
        Integer totalCount = (int)followMapper.countByExample(followExample);
        paginationDTO.setPagination(totalCount, page, size);
        if(page < 1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //5*(i-1)
        Integer offset = size*(page-1);
        for (Follow follow : followList) {
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(follow.getFollowers());
            List<User> users = userMapper.selectByExample(userExample);
            for (User user : users) {
                userList.add(user);
            }

        }
        paginationDTO.setData(userList);
        return paginationDTO;

    }
}

