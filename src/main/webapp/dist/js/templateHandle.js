// 用于模板建设的 js

// 初始化
let body = document.body;
const project_name = "/ElectricRat/";
const baseUri = window.location.origin + project_name;
(()=>{
    const Container = `<!-- Brand Logo -->
    <a href="index.html" class="brand-link">
      <img src="${baseUri}dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">电气鼠</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar"></div>
    <!-- /.sidebar -->`
    const Sidebar_Menu = `<nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="${baseUri}index.html" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                主页
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/bruteforce/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                暴力破解
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/bruteforce/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/bruteforce/bf_form.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>基于表单的暴力破解</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/bruteforce/bf_server.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>验证码绕过(on server)</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/bruteforce/bf_client.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>验证码绕过(on client)</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/bruteforce/bf_token.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>token防爆破</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/xss/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                XSS跨站脚本
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/xss/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_reflected_get.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>反射型XSS(GET)</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_reflected_post.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>反射型XSS(POST)</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_stored.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>存储型XSS</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_dom.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>DOM型XSS</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_dom_x.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>DOM型XSS-X</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_blind.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>XSS之盲打</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_01.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>XSS之过滤</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_02.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>XSS之转义实体编码</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_03.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>XSS之href输出</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xss/xss_04.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>XSS之js输出</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/csrf/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                CSRF跨站请求伪造
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/csrf/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/csrf/csrf_get.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>CSRF(GET)</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/csrf/csrf_post.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>CSRF(POST)</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/csrf/csrf_token.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>CSRF Token</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/sqli/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                SQL注入
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_id.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>数字型注入</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_str.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>字符型注入</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_fuzzy.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>模糊查询注入</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_multiple.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>条件多重标注注入</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_update.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>添加/更新语句注入</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_del.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>删除语句注入</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_blind_b.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>布尔盲注</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_blind_t.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>时间盲注</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/sqli/sqli_filter.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>过滤单引号</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/rce/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                RCE远程命令执行
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/rce/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/rce/rce_cmd.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>命令执行</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/rce/rce_ping.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>ping操作</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/urlredirect/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                URL重定向
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/urlredirect/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/urlredirect/urlredirect.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>不安全的URL跳转</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/urlredirect/urlredirect_server.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>后端校验URL</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/unsafeupload/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                任意文件上传
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/unsafeupload/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/unsafeupload/clientcheck.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>前端校验文件后缀</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/unsafeupload/servercheck.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>后端校验MIME TYPE</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/unsafeupload/blacklist.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>后端文件后缀</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/dir/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                目录遍历
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/dir/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/dir/dir_list.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>任意文件下载</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/xxe/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                XXE实体注入
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/xxe/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/xxe/xml_parse.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>提交XML解析</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/ssrf/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                SSRF服务端请求伪造
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/ssrf/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/ssrf/get_remote_img.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>远程拉取图片</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/spel/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                SpEL表达式注入
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/spel/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/spel/spel_view.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>动态使用表达式</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/ssti/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                SSTI模板注入
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/ssti/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/ssti/ssti_view.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>动态使用表达式</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/serialize/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                JAVA反序列化
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/serialize/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/serialize/serialize_view.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>动态反序列化对象</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/serialize/jndi_inject.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>JNDI注入</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/fileinclude/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                文件包含漏洞
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/fileinclude/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/fileinclude/include_local.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>本地文件包含</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/fileinclude/include_remote.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>远程文件包含</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/fileinclude/move_file.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>添加模板静态文件</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="${baseUri}pages/infoleak/index.html" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                敏感信息泄露
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${baseUri}pages/infoleak/index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>概述</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${baseUri}pages/infoleak/html_file_leak.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>页面泄露敏感文件</p>
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </nav>`
    const Navbar = `
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="${baseUri}index.html" class="nav-link">主页</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="https://github.com/linjiananallnt/ElectricRat" class="nav-link">联系作者</a>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" data-widget="fullscreen" href="#" role="button">
          <i class="fas fa-expand-arrows-alt"></i>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
          <i class="fas fa-th-large"></i>
        </a>
      </li>
    </ul>`
    const Footer = `    <strong>Copyright &copy; 2014-2021 .</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 3.2.0
    </div>`
    let container = body.querySelector("#Container")
    container.innerHTML = Container
    let sidebar = body.querySelector("#sidebar")
    sidebar.innerHTML = Sidebar_Menu
    let navbar = body.querySelector("#Navbar")
    navbar.innerHTML = Navbar
    let footer = body.querySelector("footer")
    footer.innerHTML = Footer
})()

function setWrapperHeader(fatherTitle="默认", childrenTitles=["默认"]){
    let childrenList = "";
    for (let children of childrenTitles){
        childrenList += `<li class="breadcrumb-item active">${children}</li>`
    }
    let WrapperHeader = `<div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${fatherTitle}</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="">${fatherTitle}</a></li>
              ${childrenList}
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->`
    let wrapper_header = body.querySelector("#WrapperHeader")
    wrapper_header.innerHTML = WrapperHeader
}
function generateNote(mes){
    return `<div class="callout callout-info">
              <h5><i class="fas fa-info"></i> Note:</h5>${mes}</div>`;
}
if(document.location.pathname.indexOf("index") === -1){
    let link_dom = document.createElement('a');
    link_dom.style = 'float: right;cursor: pointer;'
    link_dom.innerText = "查看源代码"
    let first_header_dom = document.getElementsByClassName('card-header')[0]
    first_header_dom.appendChild(link_dom)
    link_dom.onclick = ()=>{
        let s = document.getElementById('showSource').style;
        s = s.getPropertyValue('display') ==='' || s.getPropertyValue('display') === 'none'?s.setProperty('display', 'block'):s.setProperty('display', 'none')
    }
}
