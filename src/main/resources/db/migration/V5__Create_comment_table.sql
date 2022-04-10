create table comment
(
    id bigint auto_increment,
    parent_id bigint not null,
    type int not null,
    commentator int not null,
    gmt_create bigint not null,
    gmt_modified bigint not null,
    like_count bigint default 0,
    constraint COMMENT_PK
        primary key (id)
);

comment on column comment.parent_id is '父类id';

comment on column comment.type is '父类类型';

comment on column comment.commentator is '评论人id';