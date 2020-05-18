import createAPI from '../axios'

// 系统管理相关 API
const system = {
    // 用户基本权限
    basic: {
        userMenu: config => createAPI('/user/menu', 'get', config),
        userInfo: config => createAPI('/user/info', 'get', config),
        userLogin: config => createAPI('/user/login', 'post', config),
        changePwd: config => createAPI('/user/change-pwd', 'post', config),
    },
    // 配置项管理
    config: {
        list: config => createAPI('/admin/configure/list', 'post', config),
        save: config => createAPI('/admin/configure/save', 'post', config),
        delete: config => createAPI('/admin/configure/del', 'delete', config),

        getParamValue: config => createAPI('/admin/configure/param-value', 'post', config)
    },
    // 菜单管理
    menu: {
        map: config => createAPI('/menu/father-menus', 'get', config),
        list: config => createAPI('/menu/list', 'post', config),
        save: config => createAPI('/menu/save', 'post', config),
        delete: config => createAPI('/menu/del', 'delete', config),
    },
    // 角色管理
    role: {
        list: config => createAPI('/role/list', 'get', config),
        all: config => createAPI('/role/all', 'post', config),
        save: config => createAPI('/role/save', 'post', config),
        delete: config => createAPI('/role/del', 'delete', config),
        getMenu: config => createAPI('/role/get-menu', 'post', config),
        getUser: config => createAPI('/role/get-user', 'post', config),
        saveUser: config => createAPI('/role/save-user', 'post', config),
        savePrivilege: config => createAPI('/role/save-privilege', 'post', config),
        getResource: config => createAPI('/role/get-resource', 'post', config),
        saveResource: config => createAPI('/role/save-resource', 'post', config),
    },
    //资源权限行为
    action: {
        getClasses: config => createAPI('/action/get-classes', 'get', config),
        all: config => createAPI('/action/all', 'get', config),
        getResources: config => createAPI('/action/get-resources', 'post', config),
        batchSave: config => createAPI('/action/batch-save', 'post', config),
        delete: config => createAPI('/action/delete', 'delete', config),
    },
    // 用户管理
    user: {
        list: config => createAPI('/user/list', 'post', config),
        save: config => createAPI('/user/save', 'post', config),
        delete: config => createAPI('/user/del', 'delete', config),
        resetPwd: config => createAPI('/user/reset-pwd', 'post', config),
    },
	apply:{
		upload:config => createAPI('/file/file/upload', 'post', config),
		download:config => createAPI('/file/file/download', 'post', config),
        list: config => createAPI('/file/list', 'post', config),
	},
    test:{
        test:config => createAPI('/test/test', 'post', config),
        findUser:config => createAPI('/test/findUser', 'post', config),
        checkLogin:config => createAPI('/test/checkLogin', 'post', config),
        logout:config => createAPI('/test/logout', 'post', config),
    },
    file:{
         preview:config => createAPI('/file/file/preview', 'post', config),
    },
    // 笔记本,查,增,改,删
    note: {
        cx: config => createAPI('/note/cx', 'post', config),
        save: config => createAPI('/note/save', 'post', config),
        sc: config => createAPI('/note/sc', 'delete', config),
        cxtj1: config => createAPI('/note/cxtj1', 'post', config),
        cxtj2: config => createAPI('/note/cxtj2', 'post', config),
        import: config => createAPI('/note/import', 'post', config),
    },
    // 考勤,查,增,改,删
    wcheck: {
        cx: config => createAPI('/wcheck/cx', 'post', config),
        save: config => createAPI('/wcheck/save', 'post', config),
        sc: config => createAPI('/wcheck/sc', 'delete', config),
        import: config => createAPI('/wcheck/import', 'post', config),
        export: config => createAPI('/wcheck/export', 'post', config),
    },
};

export default system;
