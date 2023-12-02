<template>
  <div class="container mt-5">
    <h2>Lista de Medicos</h2>
    <RouterButton @navigate="navigateTo" routePath="/medico/add" buttonText="Adicionar Medico" />
    <LogoutButton/>
    <table class="table mt-3" v-if="medico.length > 0">

      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Email</th>
          <th>CRM</th>
          <th>Especialidade</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="medico in medico" :key="medico.id">
          <td><router-link :to="{ name: 'MedicoDetails', params: { id: medico.id } }">{{ medico.id }}</router-link></td>
          <td>{{ medico.nome }}</td>
          <td>{{ medico.email }}</td>
          <td>{{ medico.crm }}</td>
          <td>{{ medico.especialidade}}</td>
          <td>
            <router-link :to="{ name: 'MedicoEdit', params: { id: medico.id },query: { editing: true } }"
              class="btn btn-primary btn-sm mr-2">Editar</router-link>
            <button @click="deleteProduct(medico.id)" class="btn btn-danger btn-sm">Excluir</button>
          </td>
        </tr>
      </tbody>

    </table>
        <div class="pagination justify-content-end mt-3">
        <button @click="prevPage" :disabled="currentPage === 1" class="btn btn-secondary mr-2">Anterior</button>
        <button @click="nextPage" :disabled="currentPage * pageSize >= totalProducts" class="btn btn-secondary">Próximo</button>
      </div>
  </div>
</template>

<script>
import api from '@/api'

import RouterButton from "@/components/RouterButton.vue"
import LogoutButton from "@/components/LogoutButton.vue"

export default {
  components: {
    RouterButton,
    LogoutButton
  },
  data() {
    return {
      medico: [],
      currentPage: 1,
      pageSize: 3, // Número de itens por página
      totalProducts: 0
    }
  },
  beforeMount() {
    this.fetchProducts()
  },
  methods: {
    navigateTo(routePath) {
      this.$router.push(routePath)
    },
    async fetchProducts() {
  try {
    const response = await api.get(`/medico?page=${this.currentPage}&size=${this.pageSize}&sort=price&direction=desc`)
    this.medico = response.data.content; 
    this.totalProducts = response.data.totalElements;
  } catch (error) {
    console.error(error)
  }
},
    async deleteProduct(medicoID) {
      if (confirm('Tem certeza de que deseja excluir este produto?')) {
        try {
          await api.delete(`/medico/${medicoID}`)
          this.medico = this.medico.filter(medico => medico.id !== medicoID)
        } catch (error) {
          console.error(error)
        }
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.fetchProducts();
      }
    },
    nextPage() {
      if (this.currentPage * this.pageSize < this.totalProducts) {
        this.currentPage++;
        this.fetchProducts();
      }
    }
  },
}
</script>
<style scoped>
.pagination {
  margin-top: 20px;
}

.justify-content-end {
  justify-content: flex-end;
}
</style>
