(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-698862e9"],{"0681":function(e,t,s){"use strict";var i=s("910c"),n=s.n(i);n.a},"54e2":function(e,t,s){"use strict";s.r(t);var i=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{attrs:{id:"login"}},[s("el-row",[s("el-col",{attrs:{span:24}},[s("el-input",{attrs:{id:"email",placeholder:"请输入邮箱"},on:{change:e.checkEmail},model:{value:e.email,callback:function(t){e.email=t},expression:"email"}},[s("template",{slot:"prepend"},[e._v("邮箱")])],2)],1)],1),s("el-row",[s("el-col",{attrs:{span:24}},[s("el-input",{attrs:{id:"password",type:"password",placeholder:"请输入密码"},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}},[s("template",{slot:"prepend"},[e._v("密码")])],2)],1)],1),s("p",{staticClass:"flex"},[s("el-button",{attrs:{type:"primary"},on:{click:e.login}},[e._v("登录")]),s("el-button",{attrs:{type:"primary"},on:{click:function(t){e.$router.push("/signup")}}},[e._v("注册")])],1)],1)},n=[],o=(s("7f7f"),s("cadf"),s("551c"),s("097d"),s("7f43")),a=s.n(o),r={email:"SignIn",data:function(){return{email:"",password:""}},methods:{checkEmail:function(){var e=this.email,t=/.*?@.*?\.com/;if(!t.test(e))return this.$notify.error({title:"输入错误",message:"邮箱格式不正确"}),-1;a()({method:"post",url:"/express_site/similarEmail.action",data:{email:e}}).then(function(){console.log("succeed")}).then(function(){console.log("errorrr"),this.$notify.error({title:"输入错误",message:"邮箱不存在，是否已注册？"})})},login:function(){var e=this;function t(e){if(""===e.value)return this.$notify.error({title:"输入错误",message:"".concat(e.name,"不能为空")}),!1}var s=this.email,i=this.password,n=t({name:"邮箱",value:this.email});if(!n&&(n=t({name:"密码",value:this.password}),!n))return i.length<6?(this.$notify.error({title:"输入错误",message:"密码至少6位"}),this.password="",-1):void a()({method:"post",url:"/express_site/login.action",data:{email:s,password:i}}).then(function(t){var s=t.data;e.$store.commit("setFriends",s.friends),e.$store.commit("setUser",s.user),e.$router.push("/chat"),console.log("login succeed")}).then(function(){console.log("login failed")})}}},l=r,c=(s("0681"),s("2877")),u=Object(c["a"])(l,i,n,!1,null,"130d7a4c",null);u.options.__file="SignIn.vue";t["default"]=u.exports},"910c":function(e,t,s){}}]);