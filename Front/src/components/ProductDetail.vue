<template>
  <BackToHomeButton />
</template>

<script>
import api from '@/api'

export default {
  computed: {
    imageUrl() {
      const baseUrl = 'http://localhost:8080/medico' // Substitua pelo seu URL base
      return `${baseUrl}${this.product.image}`
    }
  },
  data() {
    return {
      product: null,
    }
  },
  mounted() {
    const productId = this.$route.params.id
    this.fetchProduct(productId)
  },
  
  methods: {
    async fetchProduct(productId) {
      try {
        const response = await api.get(`/medico/${productId}`)
        this.product = response.data
      } catch (error) {
        console.error(error)
      }
    },
    goToEditPage(productId) {
      this.$router.push({ name: 'ProductEdit', params: { id: productId }, query: { editing: true } })
    },
  },
}
</script>
