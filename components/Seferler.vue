<template>
  <div class="relative overflow-x-auto shadow-md">
    <table class="w-full text-sm text-left">
      <thead class="text-xs uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
          <th scope="col" class="px-6 py-3">Firma</th>
          <th scope="col" class="px-6 py-3">Nereden</th>
          <th scope="col" class="px-6 py-3">Nereye</th>
          <th scope="col" class="px-6 py-3">Sefer Tarihi</th>
          <th scope="col" class="px-6 py-3">Fiyat</th>
          <th scope="col" class="px-10 py-3"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(data, index) in dataTable" :key="index" class="border-b dark:border-gray-700 hover:bg-gray-100">
          <td scope="row" class="px-6 py-4">{{ data[0] }}</td>
          <td class="px-6 py-4">{{ data[1] }}</td>
          <td class="px-6 py-4">{{ data[2] }}</td>
          <td class="px-6 py-4">{{ formatDate(data[3]) }}</td>
          <td class="px-6 py-4">{{ data[5] }}₺</td>
          <td class="px-6 py-4 text-right">
            <button @click="buyTicket(data)" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Bilet
              Al</button>
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
    this.searchData = this.$route.query;
    this.fetchData();
  },
  data() {
    return {
      dataTable: [],
      input_data: "",
      searchData: []
    };
  },
  methods: {
    async fetchData() {
      await axios.post("http://localhost:8080/api/seferler/getSeferAndBiletFiyat", this.searchData)
        .then(response => {
          this.dataTable = response.data;
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        })
    },
    formatDate(dateTimeString) {
      const options = { day: 'numeric', month: 'long', hour: 'numeric', minute: 'numeric' };
      const formattedDate = new Date(dateTimeString).toLocaleDateString('tr-TR', options);
      return formattedDate;
    },
    buyTicket(data) {
      this.$router.push({
        name: 'rezervasyon',
        query: {
          firma: data[0],
          nereden: data[1],
          nereye: data[2],
          seferKalkisTarihi: data[3],
          seferVarisTarihi: data[4],
          biletFiyat: data[5],
          seferNo: data[6],
          koltukNo: data[7] + 1,
        }
      });
    },
  },
};
</script>

<style scoped></style>
