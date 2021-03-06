/*
@author: pengjia
@date: 2020/10/30
@description:
*/
// src/plugins/vuetify.js

import Vue from 'vue'
import Vuetify, {
  VCard,
  VRating,
  VToolbar,
} from 'vuetify/lib'
import { Ripple } from 'vuetify/lib/directives'

Vue.use(Vuetify, {
  components: {
    VCard,
    VRating,
    VToolbar,
  },
  directives: {
    Ripple,
  },
})

const opts = {}

export default new Vuetify(opts)
