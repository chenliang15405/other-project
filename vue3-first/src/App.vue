<template>
  <div id="app">

    <set-upcom p1="123"></set-upcom>
    <ref-com></ref-com>
    <to-refs></to-refs>
    <computed-com></computed-com>
    <watch-com></watch-com>

    <p>--------------------</p>
    <parent-com></parent-com>
  </div>
</template>

<script>
import setUpCom from './components/01_setUp'
import refCom from './components/02_ref'
import toRefCom from './components/03_toRefs'
import computedCom from './components/04_computed'
import watchCom from './components/05_watch'
import parentCom from './components/09_parentRef'

export default {
  name: 'app',
  components: {
      'set-upcom': setUpCom,
      refCom,
      'toRefs': toRefCom,
      computedCom,
      watchCom,
      parentCom
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
