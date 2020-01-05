package com.xujie.mysecret.entity.message;

import com.xujie.mysecret.entity.Article;
import lombok.Data;

import java.util.List;

@Data
public class NewsMessage extends BaseMessage {
    //图文消息个数,限制为10条以内
    private int ArticleCount;
    //多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;
}
