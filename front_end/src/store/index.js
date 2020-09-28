import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  empId: '',
  empName: '',
  nickName: '',
  token: '',
  menu: {}
}

const mutations = {
  addEmp(state, payload) {
    state.empId = payload.empId
    state.empName = payload.empName
    state.nickName = payload.nickName
    state.token = payload.token
  },
  clearAll(state) {
    state.empId = ''
    state.empName = ''
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
