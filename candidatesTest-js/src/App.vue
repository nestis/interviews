<template>
  <div id="app">
    <router-view/>
    <div class="spinner-content" v-bind:class="{ 'hidden': isLoaderHidden }">
      <h2>Cargando...</h2>
      <div class="spinner"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data: function() {
    return {
      isLoaderHidden: false
    }
  },

  created() {
    this.$eventHub.$on('hideLoader', this.hideLoader);
  },

  methods: {
    hideLoader() {
      this.isLoaderHidden = true;
    }
  }
}
</script>

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

/** Spinner styles **/
.spinner-content {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 100%;
  background: white;
  z-index: 100;
  opacity: 1;

  .spinner {
    width: 60px;
    height: 60px;
    background-color: #333;

    margin: 100px auto;
    -webkit-animation: sk-rotateplane 1.2s infinite ease-in-out;
    animation: sk-rotateplane 1.2s infinite ease-in-out;
  }

  @-webkit-keyframes sk-rotateplane {
    0% { -webkit-transform: perspective(120px); background-color: red }
    50% { -webkit-transform: perspective(120px) rotateY(180deg); background-color: red  }
    100% { -webkit-transform: perspective(120px) rotateY(180deg)  rotateX(180deg); background-color: red  }
  }

  @keyframes sk-rotateplane {
    0% { 
      transform: perspective(120px) rotateX(0deg) rotateY(0deg);
      background-color: #26a69a;
      -webkit-transform: perspective(120px) rotateX(0deg) rotateY(0deg) 
    } 50% { 
      transform: perspective(120px) rotateX(-180.1deg) rotateY(0deg);
      background-color: blue;
      -webkit-transform: perspective(120px) rotateX(-180.1deg) rotateY(0deg) 
    } 100% { 
      transform: perspective(120px) rotateX(-180deg) rotateY(-179.9deg);
      background-color: yellow;
      -webkit-transform: perspective(120px) rotateX(-180deg) rotateY(-179.9deg);
    }
  }

  &.hidden {
    opacity: 0;
    transition: opacity 500ms ease-in-out;
    z-index: -1;
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
