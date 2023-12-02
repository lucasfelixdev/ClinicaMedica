import { createRouter, createWebHistory } from 'vue-router'
import ProductList from '@/components/ProductList.vue'
import ProductDetail from '@/components/ProductDetail.vue'
import MedicoForm from '@/components/ProductForm.vue'
import LoginForm from '@/components/LoginForm.vue'

const routes = [
  { path: '/', component: LoginForm },
  
  { path: '/medico/list', component: ProductList, meta: { requiresAuth: true } },

  { path: '/medico/:id', name: 'MedicoDetails', component: ProductDetail, props: true, meta: { requiresAuth: true } },

  { path: '/medico/:id/edit', name: 'MedicoEdit', component: MedicoForm, props: route => ({
    id: route.params.id,
    editing: route.query.editing === 'true' // Converte a string 'true' para booleano true
  }), meta: { requiresAuth: true }},
  
  { path: '/medico/add', name: 'AddMedico', component: MedicoForm, props: { editing: false }, meta: { requiresAuth: true } },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

// Adiciona um guarda de navegação para verificar se o usuário está autenticado antes de acessar rotas protegidas
router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('token');

  // Verifica se a rota requer autenticação e se o usuário não está autenticado
  if (to.matched.some(record => record.meta.requiresAuth) && !isAuthenticated) {
    // Redireciona para a página de login se o usuário não estiver autenticado
    next('/');
  } else {
    // Permite a navegação para outras rotas
    next();
  }
});

export default router
