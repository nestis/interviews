import Vue from 'vue';

const EventHub = new Vue();
export default EventHub;

Vue.mixin({
    beforeCreate() {
        if (this.$options.event) {
            this.$eventHub = this.$options.eventHub;
        } else {
            this.$eventHub = (this.$parent && this.$parent.$eventHub) || this
        }
    }
})