import { VantComponent } from '../common/component';
import { button } from '../mixins/button';
import { openType } from '../mixins/open-type';
import { formType } from '../mixins/form-type';
const url_microService = require('../../config/config').url_microService; //（与config.js相对路径）
VantComponent({
  classes: ['loading-class'],
  mixins: [button, openType],
  props: {
    plain: Boolean,
    block: Boolean,
    round: Boolean,
    square: Boolean,
    loading: Boolean,
    disabled: Boolean,
    type: {
      type: String,
      value: 'default'
    },
    size: {
      type: String,
      value: 'normal'
    }
  },
  methods: {
    onClick: function onClick() {
      if (!this.data.disabled && !this.data.loading) {
        this.$emit('click');
      }
    },
    formSubmit: function(e) {
      console.log(e.detail.formId)
      var userInfo = wx.getStorageSync("simpleInfo");
      
      var token = userInfo.token;
      wx.request({
        url: url_microService+'/app/member/pushFormId', // 仅为示例，并非真实的接口地址
        data: {
          formId:e.detail.formId,
          token:token
        },
      })
    },

  }
});