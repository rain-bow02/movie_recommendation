<template>
  <div id="app">
    <header>
      <!-- 命名视图 -->
      <router-view name="header" />
    </header>
    <main>
      <router-view />
    </main>
    <footer class="footer">
      <!-- 命名视图 -->
      <router-view name="footer" />
    </footer>
  </div>
</template>

<script>
export default {
  name: "app", //组件名称
};
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
.footer {
  height: 50px;
}
</style>
