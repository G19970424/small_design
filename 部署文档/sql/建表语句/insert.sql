-- 角色
INSERT INTO t_tis_role (id, role_code, role_name, role_description) VALUES (1, 'admin', '管理员', '管理员，拥有所有权限');
INSERT INTO t_tis_role (id, role_code, role_name, role_description) VALUES (2, 'user', '普通用户', '普通用户，拥有部分权限');
-- 权限
INSERT INTO t_tis_permission (id, permission_code, permission_name) VALUES (1, 'create_user', '创建用户');
INSERT INTO t_tis_permission (id, permission_code, permission_name) VALUES (2, 'query_user', '查看用户');
INSERT INTO t_tis_permission (id, permission_code, permission_name) VALUES (3, 'delete_user', '删除用户');
INSERT INTO t_tis_permission (id, permission_code, permission_name) VALUES (4, 'modify_user', '修改用户');
-- 请求路径
INSERT INTO t_tis_request_path (id, url, description) VALUES (1, '/getUser', '查询用户');
-- 用户角色关联关系
INSERT INTO t_tis_user_role_relation (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO t_tis_user_role_relation (id, user_id, role_id) VALUES (2, 2, 2);
-- 角色权限关联关系
INSERT INTO t_tis_role_permission_relation (id, role_id, permission_id) VALUES (1, 1, 1);
INSERT INTO t_tis_role_permission_relation (id, role_id, permission_id) VALUES (2, 1, 2);
INSERT INTO t_tis_role_permission_relation (id, role_id, permission_id) VALUES (3, 1, 3);
INSERT INTO t_tis_role_permission_relation (id, role_id, permission_id) VALUES (4, 1, 4);
INSERT INTO t_tis_role_permission_relation (id, role_id, permission_id) VALUES (5, 2, 1);
INSERT INTO t_tis_role_permission_relation (id, role_id, permission_id) VALUES (6, 2, 2);
-- 请求路径权限关联关系
INSERT INTO t_tis_request_path_permission_relation (id, url_id, permission_id) VALUES (null, 1, 2);