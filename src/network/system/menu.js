import {postJson,get} from "../request";

export const menuHttp = {
  tree:function () {
    let params = {
      url:'/menu/tree'
    }
    return get(params)
  },
  add:function (menu) {
    let params = {
      url:'/menu/add',
      data:{
        'menuName':menu.menuName,
        'perms':menu.perms,
        'pid':menu.pid,
        'url':menu.url,
        'icon':menu.icon,
        'pidList':menu.pidList,
        'menuType':menu.menuType,
        'isShow':menu.isShow
      }
    }
    return postJson(params)
  },
  del:function (menuId) {
    let params = {
      url:'/menu/del',
      data:{
        'menuId':menuId
      }
    }
    return postJson(params)
  },
  edit:function (menu) {
    let params = {
      url:'/menu/edit',
      data:{
        'menuId':menu.menuId,
        'menuName':menu.menuName,
        'perms':menu.perms,
        'url':menu.url,
        'icon':menu.icon,
        'pid':menu.pid,
        'pidList':menu.pidList,
        'menuType':menu.menuType,
        'isShow':menu.isShow
      }
    }
    return postJson(params)
  },
  get:function (menuId) {
    let params = {
      url:'/menu',
      data:{
        'menuId':menuId
      }
    }
    return get(params)
  }
}
