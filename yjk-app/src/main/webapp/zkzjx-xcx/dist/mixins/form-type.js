export var openType = Behavior({
  properties: {
    formType: String
  },
  methods: {
    bindFormSubmit: function bindFormSubmit(event) {
      console.log(e)
      this.$emit('formSubmit', event.detail);
    },

  }
});