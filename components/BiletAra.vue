<template>
  <div class="flex items-center justify-center h-screen">
    <div class="max-w-md p-4">
      <h1 class="text-2xl font-bold mb-4">Şehirler Arası Otobüs Bilet Sistemi</h1>

      <div class="mb-4">
        <label for="from" class="block text-l font-medium text-gray-600">Nereden:</label>
        <select v-model="from" id="from" name="from" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
          <option value="" disabled selected>Lütfen seçiniz</option>
          <option v-for="city in fromCities" :key="city" :value="city">{{ city.Sehir }}</option>
        </select>
      </div>
      <div class="mb-4 flex justify-center">
        <button @click="switchCities" class="bg-blue-500 text-white p-2 rounded-md">
          <svg fill="#131717" width="20" height="20" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path d="M6.99 11L3 15l3.99 4v-3H14v-2H6.99v-3zM21 9l-3.99-4v3H10v2h7.01v3L21 9z"></path>
          </svg>
        </button>
      </div>
      <div class="mb-4">
        <label for="to" class="block text-l font-medium text-gray-600">Nereye:</label>
        <select v-model="to" id="to" name="to" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
          <option value="" disabled selected>Lütfen seçiniz</option>
          <option v-for="city in toCities" :key="city" :value="city">{{ city.Sehir }}</option>
        </select>
      </div>
      <div class="mb-4">
        <label for="date" class="block text-l font-medium text-gray-600">Tarih:</label>
        <input v-model="date" type="date" id="date" name="date" class="mt-1 p-2 border border-gray-300 rounded-md w-full"
          :min="today" :disabledDates="disabledDates">
      </div>

      <button @click="searchTicket" class="bg-blue-500 text-white p-2 rounded-md w-full">Bilet Ara</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  mounted() {
    this.fetchData();

    this.date = this.today;

  },
  data() {
    return {
      today: new Date().toISOString().split('T')[0],
      from: '',
      to: '',
      date: '',
      fromCities: [],
      toCities: [],
      disabledDates: {
        to: new Date().toISOString().split('T')[0]
      }
    };
  },
  methods: {
    async fetchData() {
      await axios.get('http://localhost:8080/api/seferler/nereden')
        .then(response => {
          this.fromCities = response.data;
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });

      await axios.get('http://localhost:8080/api/seferler/nereye')
        .then(response => {
          this.toCities = response.data;
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });

      this.from = this.fromCities.find(city => city.Sehir === 'İstanbul');
      this.to = this.toCities.find(city => city.Sehir === 'Ankara');

    },

    searchTicket() {
      this.$router.push({
        name: 'seferler',
        query: {
          from: this.from.Sehir,
          to: this.to.Sehir,
          date: this.date
        }
      });

    },
    switchCities() {
      const temp = this.from;
      this.from = this.to;
      this.to = temp;
    },

  }
};
</script>

<style scoped></style>
