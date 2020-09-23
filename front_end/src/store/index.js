import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
<<<<<<< HEAD
  userId: '',
  accountId: '',
  nickName: '',
  token: '',
  menu: {}
=======
  userId : '',
  accountId : '',
  nickName : '',
  token : '',
  menu : {}
>>>>>>> 2fc5703a50f56e73314c210aafd8c785be73ad92
}

const mutations = {
  addUser(state, payload) {
    state.userId = payload.userId
    state.accountId = payload.accountId
    state.nickName = payload.nickName
    state.token = payload.token
  },
  clearAll(state) {
    state.userId = ''
    state.accountId = ''
    state.nickName = ''
    state.token = ''
  },
  addMenu(state, payload) {
    state.menu = payload
  }
}

const store = new Vuex.Store({
  state,mutations
})

export default store
