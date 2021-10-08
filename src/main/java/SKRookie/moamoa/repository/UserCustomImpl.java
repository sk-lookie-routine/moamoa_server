package com.mcloudoc.aidoc.webserver.web.repository;

import SKRookie.moamoa.dto.UserSearchCondition;
import SKRookie.moamoa.entity.User;
import SKRookie.moamoa.repository.UserCustom;
import SKRookie.moamoa.repository.support.Querydsl4RepositorySupport;
import com.mcloudoc.aidoc.webserver.web.dto.CategorySearchCondition;
import com.mcloudoc.aidoc.webserver.web.entity.Category;
import com.mcloudoc.aidoc.webserver.web.enums.CategoryType;
import com.mcloudoc.aidoc.webserver.web.repository.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.mcloudoc.aidoc.webserver.web.entity.QCategory.category;
import static sun.java2d.loops.SurfaceType.Custom;

public class UserCustomImpl extends Querydsl4RepositorySupport implements UserCustom {
    public CategoryCustomImpl() {
        super(Category.class);
    }

    @Override
    public Page<User> search(UserSearchCondition condition, Pageable pageable) {
        return applyPagination(pageable, contentQuery ->contentQuery
                .selectFrom(category)
                .where(
                        domainIdEq(condition.getDomainIdEq()),
                        categoryTypeEq(condition.getCategoryTypeEq()),
                        parentIdEq(condition.getParentIdEq())

                ), countQuery -> countQuery
                .select(category.id)
                .from(category)
                .where(
                        domainIdEq(condition.getDomainIdEq()),
                        categoryTypeEq(condition.getCategoryTypeEq()),
                        parentIdEq(condition.getParentIdEq())

                )
        );
    }

    private BooleanExpression domainIdEq(Long domain_id) {
        return domain_id != null ? category.domain.id.eq(domain_id) : null;
    }

    private BooleanExpression categoryTypeEq(CategoryType categoryType) {

        return categoryType != null ? category.categoryType.eq(categoryType) : null;
    }

    private BooleanExpression parentIdEq(Long parent_id) {

        return parent_id != null ? category.parentId.eq(parent_id) : null;
    }
}
