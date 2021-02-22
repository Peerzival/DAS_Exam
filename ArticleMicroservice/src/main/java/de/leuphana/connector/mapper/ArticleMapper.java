package de.leuphana.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.component.article.structure.Article;
import de.leuphana.connector.entity.ArticleEntity;

public class ArticleMapper {
	private static Mapper mapper;
	
	static {
		mapper = DozerBeanMapperSingletonWrapper.getInstance();
	}
	
	public static Article mapArticleEntityToArticle(ArticleEntity articleEntity) {
		
//		Article article = new Article();
//		article.setArticleId(articleEntity.getArticleId());
//		article.setManufactor(articleEntity.getManufactor());
//		article.setPrice(articleEntity.getPrice());
//		article.setName(articleEntity.getName());
		
		return mapper.map(articleEntity, Article.class);
	}

	public static ArticleEntity mapArticleToArticleEntity(Article article) {
		return mapper.map(article, ArticleEntity.class);
	}

}
