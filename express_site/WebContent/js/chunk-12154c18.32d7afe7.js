(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-12154c18"],{"1f66":function(t,e,s){t.exports=s.p+"img/3.de7678da.png"},"3b99":function(t,e,s){"use strict";var a=s("4271"),i=s.n(a);i.a},4271:function(t,e,s){},"796e":function(t,e,s){"use strict";var a=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"outer"},[a("i",{staticClass:"line-top"}),a("div",{staticClass:"container"},[a("img",{staticClass:"profile left",attrs:{src:s("1f66"),alt:"Alex"}}),a("section",{staticClass:"desc left"},[a("p",{staticClass:"name"},[t._v(t._s(t.name))]),a("p",{staticClass:"wechatid"},[t._v("WechatID: "+t._s(t.wechatid))])]),a("p",{staticClass:"arrow iconfont right"},[t._v("")]),a("p",{staticClass:"qrcode iconfont right"},[t._v("")])]),a("i",{staticClass:"line-bottom"})])},i=[],n={name:"MeProfile",props:{wechatid:{type:String},profile:{type:String},name:{type:String}}},c=n,r=(s("835b"),s("2877")),o=Object(r["a"])(c,a,i,!1,null,"2bdecf5a",null);o.options.__file="MeProfile.vue";e["a"]=o.exports},"835b":function(t,e,s){"use strict";var a=s("b9a7"),i=s.n(a);i.a},8558:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("MeItem",{staticStyle:{"margin-top":"20px"},attrs:{name:t.searchedUser.name,wechatid:t.searchedUser.email}}),s("el-button",{staticStyle:{margin:"20px calc((100% - 70px) / 2)"},attrs:{type:"primary"},on:{click:t.add}},[t._v("添加")])],1)},i=[],n=(s("cadf"),s("551c"),s("097d"),s("7f43")),c=s.n(n),r=s("796e"),o={name:"AddFriend",components:{MeItem:r["a"]},data:function(){return{searchedUser:this.$store.state.searchedUser}},methods:{add:function(){var t=this;c()({method:"put",url:"/express_site/addfriend.action",data:{id:this.$store.state.searchedUser.id}}).then(function(e){console.log("添加成功",e);var s=t.$store.state.searchedUser;t.$store.commit("addFriend",s),t.$router.push("/contacts")}).catch(function(t){alert("确认添加好友请求出错",t)})}}},d=o,l=(s("3b99"),s("2877")),u=Object(l["a"])(d,a,i,!1,null,"6ed1d392",null);u.options.__file="AddFriend.vue";e["default"]=u.exports},b9a7:function(t,e,s){}}]);