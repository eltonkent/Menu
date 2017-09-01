/*-
 * #%L
 * BroadleafCommerce Menu
 * %%
 * Copyright (C) 2009 - 2017 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.menu.admin.server.security.service;

import org.broadleafcommerce.openadmin.server.security.service.AbstractAdminSecurityContributor;
import org.broadleafcommerce.openadmin.server.security.service.AdminSecurityContributor;
import org.broadleafcommerce.openadmin.server.security.service.domain.AdminPermissionDTO;
import org.broadleafcommerce.openadmin.server.security.service.type.PermissionType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("blMenuAdminSecurityContributor")
public class MenuAdminSecurityContributor extends AbstractAdminSecurityContributor implements AdminSecurityContributor {

    @Override
    protected void createAdminModules() {}

    @Override
    protected void createAdminSections() {
        ArrayList<AdminPermissionDTO> permissions = new ArrayList<>();
        permissions.add(createPermission("PERMISSION_MENU", PermissionType.READ));
        permissions.add(createPermission("PERMISSION_MENU", PermissionType.ALL));
        // INSERT INTO BLC_ADMIN_SECTION (ADMIN_SECTION_ID, CEILING_ENTITY, ADMIN_MODULE_ID, NAME, SECTION_KEY, URL, USE_DEFAULT_HANDLER, DISPLAY_ORDER) VALUES (-27000, 'org.broadleafcommerce.menu.domain.Menu', -2, 'Menus', 'Menus', '/menu', TRUE, 3000);
        createSection("Menus", MenuSectionKeys.MENU, "/menu", "org.broadleafcommerce.menu.domain.Menu", ModuleKeys.CONTENT, 3000, permissions);
    }

    @Override
    protected void createAdminPermissionEntities() {
        AdminPermissionDTO viewPermission = createPermission("PERMISSION_READ_MENU", PermissionType.READ);
        AdminPermissionDTO maintainPermission = createPermission("PERMISSION_ALL_MENU", PermissionType.ALL);
        List<String> entities = Arrays.asList(
                "org.broadleafcommerce.menu.domain.Menu",
                "org.broadleafcommerce.menu.domain.MenuItem"
        );
        createAdminPermissionEntitiesForPermission(viewPermission, entities);
        createAdminPermissionEntitiesForPermission(maintainPermission, entities);
    }

    protected class MenuSectionKeys {
        public static final String MENU = "Menus";
    }
}
