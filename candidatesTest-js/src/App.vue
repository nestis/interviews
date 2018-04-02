<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

/* Custom Stylesheet */
/**
 * Use this file to override Materialize files so you can update
 * the core Materialize files in the future
 *
 * Made By MaterializeCSS.com
 */

nav ul a,
nav .brand-logo {
  color: #444;
}

p {
  line-height: 2rem;
}

.sidenav-trigger {
  color: #26a69a;
}

.parallax-container {
  min-height: 290px;
  line-height: 0;
  height: auto;
  color: rgba(255,255,255,.9);

  .parallax img {
    opacity: 1;
  }
}
  .parallax-container .section {
    width: 100%;
  }

@media only screen and (max-width : 992px) {
  .parallax-container .section {
    position: absolute;
    top: 40%;
  }
  #index-banner .section {
    top: 10%;
  }
}

@media only screen and (max-width : 600px) {
  #index-banner .section {
    top: 0;
  }
}

.icon-block {
  padding: 0 15px;
}
.icon-block .material-icons {
  font-size: inherit;
}

footer.page-footer {
  margin: 0;
}

</style>
