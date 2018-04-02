<template>
  <div class="home">
    <div id="index-banner" class="parallax-container">
      <div class="section no-pad-bot">
        <div class="container">
          <h1 class="header center teal-text text-lighten-2">Bienvenido {{candidate}}!</h1>
          <div class="row center">
            <h5 class="header col s12 light">Bienvenido a tu test personalizado de conocimientos</h5>
          </div>
        </div>
      </div>
      <div class="parallax"><img src="img/background5.jpg" alt="Unsplashed background img 1"></div>
    </div>

    <div class="container">
      <div class="section">
        <div class="row">
          <div class="col s12 m4">
            <div class="icon-block">
              <h2 class="center brown-text"><i class="material-icons">timelapse</i></h2>
              <h5 class="center">Tiempo disponible</h5>
              <p class="light">{{time}} minutos</p>
            </div>
          </div>
          <div class="col s12 m4">
            <div class="icon-block">
              <h2 class="center brown-text"><i class="material-icons">supervisor_account</i></h2>
              <h5 class="center">Evaluador Técnico</h5>
              <p class="light">{{evaluator}}</p>
            </div>
          </div>
          <div class="col s12 m4">
            <div class="icon-block">
              <h2 class="center brown-text"><i class="material-icons">sentiment_very_satisfied</i></h2>
              <h5 class="center">Mucha suerte!</h5>
              <p class="light">Y no hagas trampas! :)</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
        <div class="row-center">
          <router-link to="test" id="download-button" class="waves-effect waves-light btn-large teal lighten-1">
            Comenzar
          </router-link>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  props: {
    candidate: String,
    evaluator: String,
    time: Number
  }
};
</script>
