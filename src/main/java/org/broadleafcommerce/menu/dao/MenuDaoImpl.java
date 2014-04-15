/*
 * #%L
 * BroadleafCommerce Menu
 * %%
 * Copyright (C) 2009 - 2014 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.broadleafcommerce.menu.dao;

import org.broadleafcommerce.menu.domain.Menu;
import org.broadleafcommerce.menu.domain.MenuImpl;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Repository("blMenuDao")
public class MenuDaoImpl implements MenuDao {
    
    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    @Override
    public Menu readMenuById(Long menuId) {
        return em.find(MenuImpl.class, menuId);
    }

    @Override
    public Menu readMenuByName(String menuName) {
        TypedQuery<Menu> query = em.createNamedQuery("BC_READ_MENU_BY_NAME", Menu.class);
        query.setParameter("menuName", menuName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Cms");

        List<Menu> results = query.getResultList();
        if (results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
