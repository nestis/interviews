import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    evaluator: '',
    candidate: '',
    minutes: 0,
    questions: {}
  },
  mutations: {
    setInitData(state, initData) {
      state.token = initData.token;
      state.evaluator = initData.leader;
      state.candidate = initData.name;
      state.minutes = initData.minutes;
      state.questions = initData.questions;
    }
  },
  actions: {
    setInitData({commit}, payload) {
      commit('setInitData', payload);
    }
  }
})
