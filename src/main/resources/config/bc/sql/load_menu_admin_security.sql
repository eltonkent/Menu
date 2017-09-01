----------------------------------------------------------------------------------------------------------------------------
-- The range of IDs available for the Menu module is [ -27,000 to -27,999 ] 
-- (see https://github.com/BroadleafCommerce/docs-internal/edit/master/Module%20IDs.md 
----------------------------------------------------------------------------------------------------------------------------

INSERT INTO BLC_ADMIN_PERMISSION (ADMIN_PERMISSION_ID, DESCRIPTION, NAME, PERMISSION_TYPE) VALUES (-27000,'Read Menu','PERMISSION_READ_MENU', 'READ');
INSERT INTO BLC_ADMIN_PERMISSION (ADMIN_PERMISSION_ID, DESCRIPTION, NAME, PERMISSION_TYPE) VALUES (-27001,'All Menu','PERMISSION_ALL_MENU', 'ALL');

-- Friendly permissions
INSERT INTO BLC_ADMIN_PERMISSION (ADMIN_PERMISSION_ID, DESCRIPTION, NAME, PERMISSION_TYPE, IS_FRIENDLY) VALUES (-27002,'View Menus','PERMISSION_MENU', 'READ', TRUE);
INSERT INTO BLC_ADMIN_PERMISSION_XREF (ADMIN_PERMISSION_ID, CHILD_PERMISSION_ID) VALUES (-27002, -27000);

INSERT INTO BLC_ADMIN_PERMISSION (ADMIN_PERMISSION_ID, DESCRIPTION, NAME, PERMISSION_TYPE, IS_FRIENDLY) VALUES (-27003,'Maintain Menus','PERMISSION_MENU', 'ALL', TRUE);
INSERT INTO BLC_ADMIN_PERMISSION_XREF (ADMIN_PERMISSION_ID, CHILD_PERMISSION_ID) VALUES (-27003, -27001);

--
-- Mapping from Roles to permissions
-- Site admins and content editors are allowed to do everything menus
--
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-27003);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-5,-27003);
