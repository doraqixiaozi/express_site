(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-70c8f880"],{"189c":function(e,t,s){"use strict";var n=s("750f"),o=s.n(n);o.a},"54e2":function(e,t,s){"use strict";s.r(t);var n=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{attrs:{id:"login"}},[s("el-row",[s("el-col",{attrs:{span:24}},[s("el-input",{attrs:{id:"email",placeholder:"请输入帐号"},model:{value:e.userName,callback:function(t){e.userName=t},expression:"userName"}},[s("template",{slot:"prepend"},[e._v("帐号")])],2)],1)],1),s("el-row",[s("el-col",{attrs:{span:24}},[s("el-input",{attrs:{id:"password",type:"password",placeholder:"请输入密码"},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}},[s("template",{slot:"prepend"},[e._v("密码")])],2)],1)],1),s("p",{staticClass:"flex"},[s("el-button",{attrs:{type:"primary"},on:{click:e.check}},[e._v("登录")]),s("el-button",{attrs:{type:"primary"},on:{click:function(t){e.$router.push("/signup")}}},[e._v("注册")])],1)],1)},o=[],a=(s("cadf"),s("551c"),s("097d"),s("7f43")),r=s.n(a),l={email:"SignIn",data:function(){return{userName:"",password:""}},methods:{check:function(e){var t=this.userName,s=this.password,n=/.*?@.*?\.com/;""!==t&&n.test(t)?""!==s?r()({method:"post",url:"/express_site/login.action",data:{userName:t,password:s}}).then(function(){console.log("succeed")}).then(function(){console.log("failed")}):this.$notify.error({title:"输入错误",message:"密码不能为空"}):this.$notify.error({title:"输入错误",message:"邮箱格式不正确"})}}},i=l,c=(s("189c"),s("2877")),p=Object(c["a"])(i,n,o,!1,null,"307affd3",null);p.options.__file="SignIn.vue";t["default"]=p.exports},"750f":function(e,t,s){}}]);