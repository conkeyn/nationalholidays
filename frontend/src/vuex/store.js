import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  nation: 'cn',
  year: 2017
}

export default new Vuex.Store({
  state
})

/**
const mutations = {
  NATION: (state, data) => {
    state.nation = data;
  }
}

export default new Vuex.Store({
  state,
  mutations
})
*/
