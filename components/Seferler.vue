<template>
  <div class="relative overflow-x-auto shadow-md">
    <table class="w-full text-sm text-left">
      <thead class="text-xs uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
          <th scope="col" class="px-6 py-3">Firma</th>
          <th scope="col" class="px-6 py-3">Nereden</th>
          <th scope="col" class="px-6 py-3">Nereye</th>
          <th scope="col" class="px-10 py-3">Sefer Tarihi</th>
          <th scope="col" class="px-10 py-3">Varis Tarihi</th>
          <th scope="col" class="px-10 py-3"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(data, index) in dataTable" :key="index" class="border-b dark:border-gray-700 hover:bg-gray-100">
          <td scope="row" class="px-6 py-4">{{ data.firma.firmaAdi }}</td>
          <td class="px-6 py-4">{{ data.seferKalkisYeri }}</td>
          <td class="px-6 py-4">{{ data.seferVarisYeri }}</td>
          <td class="px-6 py-4">{{ formatDate(data.seferKalkisSaati) }}</td>
          <td class="px-6 py-4">{{ formatDate(data.seferVarisSaati) }}</td>
          <td class="px-6 py-4 text-right">
            <router-link class="font-medium text-blue-600 dark:text-blue-500 hover:underline" :to="{ path: '/rezervasyon'}">Bilet Al</router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  mounted() {
    this.fetchData();
   console.log(this.$route.params);
  },
  data() {
    return {
      dataTable: [],
      input_data: "",
    };
  },
  methods: {
    async fetchData() {
      await axios.get('http://localhost:8080/api/seferler')
        .then(response => {
          this.dataTable = response.data;
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    },
    formatDate(dateTimeString) {
      const options = { day: 'numeric', month: 'long', hour: 'numeric', minute: 'numeric' };
      const formattedDate = new Date(dateTimeString).toLocaleDateString('tr-TR', options);
      return formattedDate;
    },
  },
};
</script>

<style scoped>
</style>
