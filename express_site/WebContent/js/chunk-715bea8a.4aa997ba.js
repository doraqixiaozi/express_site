(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-715bea8a"],{"187f":function(e,t,o){"use strict";var n=o("e06b"),a=o.n(n);a.a},8558:function(e,t,o){"use strict";o.r(t);var n=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("input",{directives:[{name:"model",rawName:"v-model",value:e.email,expression:"email"}],attrs:{type:"text",name:"adduser",placeholder:"邮箱"},domProps:{value:e.email},on:{input:function(t){t.target.composing||(e.email=t.target.value)}}}),o("button",{on:{click:e.search}},[e._v("搜索")])])},a=[],s=(o("cadf"),o("551c"),o("097d"),o("7f43")),i=o.n(s),c={name:"AddFriend",data:function(){return{email:""}},methods:{search:function(){var e=this;if(!/.{3,13}@.*?.com/.test(this.email))return this.$notify.error({title:"输入错误",message:"邮箱格式不正确"}),-1;i.a.post("/express_site/addfriends.action",{email:this.email}).then(function(t){console.log("--------------------------"),console.log(t),console.log(t),console.log(t),console.log(t),console.log(t);var o=t;e.$store.state.searchedUser=o,e.$router.push("/searchedContact")}).catch(function(e){console.log("添加好友请求出错",e)})}}},r=c,l=(o("187f"),o("2877")),u=Object(l["a"])(r,n,a,!1,null,"74ef11ea",null);u.options.__file="AddFriend.vue";t["default"]=u.exports},e06b:function(e,t,o){}}]);