<template>
  <BackToHomeButton />
  <div class="container mt-5">
    <h2>{{ editing ? 'Editar Medico' : 'ADICIONAR MÉDICO' }}</h2>

    <form @submit.prevent="saveMedic">
      <div class="form-group">
        <label for="nome">Nome do médico</label>
        <input type="text" class="form-control" id="nome" v-model="editedMedico.nome" required :readonly="shouldFieldBeReadonly('nome')">
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="text" class="form-control" id="crm" v-model="editedMedico.email" required :readonly="shouldFieldBeReadonly('email')">
      </div>

      <div class="form-group">
        <label for="crm">CRM</label>
        <input type="text" class="form-control" id="crm" v-model="editedMedico.crm" required :readonly="shouldFieldBeReadonly('crm')" :class="{ 'campo-bloqueado': shouldFieldBeReadonly('crm')}">
      </div>

      <div class="form-group">
        <label for="telefone">Telefone</label>
        <input type="text" class="form-control" id="telefone" v-model="editedMedico.telefone" required :readonly="shouldFieldBeReadonly('telefone')">
      </div>

      <div class="form-group">
        <label for="especialidade">Especialidade</label>
        <input type="text" class="form-control" id="especialidade" v-model="editedMedico.especialidade" required :readonly="shouldFieldBeReadonly('especialidade')">
      </div>

      <p>ENDEREÇO:</p>

      <div class="form-group">
  <label for="logradouro">Logradouro</label>
  <input type="text" class="form-control" id="logradouro" v-model="editedMedico.endereco.logradouro" required :readonly="shouldFieldBeReadonly('endereco.logradouro')">
</div>

<div class="form-group">
  <label for="bairro">Bairro</label>
  <input type="text" class="form-control" id="bairro" v-model="editedMedico.endereco.bairro" required :readonly="shouldFieldBeReadonly('endereco.bairro')">
</div>

<div class="form-group">
  <label for="cep">Cep</label>
  <input type="text" class="form-control" id="cep" v-model="editedMedico.endereco.cep" required :readonly="shouldFieldBeReadonly('endereco.cep')">
</div>

<div class="form-group">
  <label for="cidade">Cidade</label>
  <input type="text" class="form-control" id="cidade" v-model="editedMedico.endereco.cidade" required :readonly="shouldFieldBeReadonly('endereco.cidade')">
</div>

<div class="form-group">
  <label for="uf">Uf</label>
  <input type="text" class="form-control" id="uf" v-model="editedMedico.endereco.uf" required :readonly="shouldFieldBeReadonly('endereco.uf')">
</div>

<div class="form-group">
  <label for="numero">Numero</label>
  <input type="text" class="form-control" id="numero" v-model="editedMedico.endereco.numero" required :readonly="shouldFieldBeReadonly('endereco.numero')">
</div>

<div class="form-group">
  <label for="complemento">Complemento</label>
  <input type="text" class="form-control" id="complemento" v-model="editedMedico.endereco.complemento" required :readonly="shouldFieldBeReadonly('endereco.complemento')">
</div>

      <button type="submit" class="btn btn-primary">{{ editing ? 'Salvar' : 'Adicionar' }}</button>

    </form>
  </div>
</template>

<script>
import api from '@/api';

export default {
  props: {
    editing: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      editedMedico: {
        nome: '',
        email: '',
        crm: '',
        telefone: '',
        especialidade: '',
        endereco: {
          logradouro: '',
          bairro: '',
          cep: '',
          cidade: '',
          uf: '',
          numero: '',
          complemento: '',
        },
      },
      camposBloqueados: {
        nome: false,
        email: false,
        crm: false,
        telefone: false,
        especialidade: false,
        endereco: {
          logradouro: false,
          bairro: false,
          cep: false,
          cidade: false,
          uf: false,
          numero: false,
          complemento: false,
        },
      },
    };
  },
  mounted() {
    if (this.$route.params.id) {
      this.fetchMedico(this.$route.params.id);
    }
  },
  methods: {
    async fetchMedico(MedicoId) {
      try {
        const response = await api.get(`/medico/${MedicoId}`);
        this.editedMedico = response.data;
        console.log(this.editedMedico)

        // Bloqueia os campos que não podem ser editados durante a edição
        this.camposBloqueados = {
          nome: false,
          email: false,
          crm: true,
          telefone: false,
          especialidade: true,
          endereco: {
            logradouro: true,
            bairro: true,
            cep: true,
            cidade: true,
            uf: true,
            numero: true,
            complemento: true,
          },
        };
      } catch (error) {
        console.error(error);
      }
    },

    shouldFieldBeReadonly(fieldName) {
        return this.camposBloqueados[fieldName];
      },

      async saveMedic() {
        try {
          // Pega todos os dados do form e salva caso esteja adicionando
          const requestBody = {
            nome: this.editedMedico.nome,
            email: this.editedMedico.email,
            crm: this.editedMedico.crm,
            telefone: this.editedMedico.telefone,
            especialidade: this.editedMedico.especialidade,
            endereco: {
              logradouro: this.editedMedico.endereco.logradouro,
              bairro: this.editedMedico.endereco.bairro,
              cep: this.editedMedico.endereco.cep,
              cidade: this.editedMedico.endereco.cidade,
              uf: this.editedMedico.endereco.uf,
              numero: this.editedMedico.endereco.numero,
              complemento: this.editedMedico.endereco.complemento,
            },
          };

          if (this.$route.params.id) {
            // Caso o id exista eu coloquei para buscar o ID e editar somente os campos que devem ser editados
            const idMedico = this.$route.params.id;
            const Editar = {
              id: idMedico,
              nome: this.editedMedico.nome,
              email: this.editedMedico.email,
              telefone: this.editedMedico.telefone,
              endereco: {
                logradouro: this.editedMedico.endereco.logradouro,
                bairro: this.editedMedico.endereco.bairro,
                cep: this.editedMedico.endereco.cep,
                cidade: this.editedMedico.endereco.cidade,
                uf: this.editedMedico.endereco.uf,
                numero: this.editedMedico.endereco.numero,
                complemento: this.editedMedico.endereco.complemento,
              },
            };

            await api.put(`/medico`, Editar);
            
          } else {
            await api.post('/medico', requestBody);
          }
          this.$router.push('/medico/list');
        } catch (error) {
          console.error(error);
        }
      },
    },
  };
</script>

<style scoped>

.campo-bloqueado:not(.em-edicao), input[readonly]{
  background-color: #a8a8a8; /* Cinza claro */
  cursor: not-allowed;
}

.form-group label{
  color: rgb(0, 0, 0);
  font-size: 17px;
  font-weight: 500px;
  display: flex;
  margin-left: 20px;
}

.btn{
  margin-top: 30px;
  padding: 0 40px 0 40px;
  font-size: 25px;
}

form p{
  width: 95%;
  margin: auto;
  padding: 8px;
  color: aliceblue;
  font-weight: bold;
  margin-top: 60px;
  background-color:#0d6efd;
  margin-bottom: 20px;
}

 form{
  background-color: rgba(203, 203, 203, 0.342);
  width: 50%;
  margin: auto;
  padding: 30px 20px 30px 20px;
  margin-bottom: 60px;
  border-radius: 20px;
  border: 3px solid #0d6efd;
 }

h2{
  color: #0d6efd;
  font-weight: 800px;
}

</style>
