<template>
  <!-- Aqui alterei para que o botão back n voltasse para o login, mas para a listagem. -->
  <router-link to="/medico/list" class="btn btn-success">Voltar ao Início</router-link>
</template>

<script>
export default {
  name: 'BackToHomeButton'
};
</script>

<style scoped>
/* Estilos específicos do componente podem ser adicionados aqui */
</style>
