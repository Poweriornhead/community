package lift.ydq.commuity.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YDQ
 * @create 2022-04-05 14:45
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }

        this.page = page;

        pages.add(page);
        for (int i = 1 ; i <= 3; i++){
            if (page - i > 0){
                pages.add(0, page - i);
            }
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }


        //是否展示上一页
        if(page == 1){
            showPrevious = false;
        }else
            showPrevious = true;

        //是否展示下一页
        if(page == totalPage){
            showNext = false;
        }else
            showNext = true;

        //是否展示展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else
            showFirstPage = true;

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else
            showEndPage = true;

    }
}
