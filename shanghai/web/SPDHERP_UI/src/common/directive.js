import Vue from 'vue';
import store from '../vuex/store';
//指令钩子函数：
    /*
    bind   只调用一次，指令第一次绑定到元素的时调用
    inserted 被指令绑定指令的元素插入 父节点的时候调用
    update  被绑定的元素模版只要发生变化，就会触发（通过比较模板变化前后）
    componentUpdated 被绑定元素所在模版完成一次更新周期时被触发
    unbind 指令被解除绑定的时候
    */
Vue.directive('focus', {
    inserted: function (el, { value }) {
        if (value) {
            el.focus();
        }
    }
});
Vue.directive('gy-permission', {
    bind: function (el, binding) {
        if (binding.value) {
            var els = store.getters.getPageEls;
            if(els.indexOf(binding.value) < 0){
                el.parentNode.removeChild(el);
            }
        }
    }
});
