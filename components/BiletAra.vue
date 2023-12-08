<template>
  <div class="flex items-start justify-start h-screen p-4">
    <div class="w-full max-w-md">
      <div class="mb-4 flex items-center">
        <label for="from" class="mr-2 text-sm font-medium text-gray-600">Nereden:</label>
        <select v-model="from" id="from" name="from" class="p-2 border border-gray-300 rounded-md">
          <option value="" disabled selected>Lütfen seçiniz</option>
          <option v-for="city in fromCities" :key="city" :value="city">{{ city.Sehir }}</option>
        </select>
      </div>

      <div class="flex items-center mb-4">
        <label for="to" class="mr-2 text-sm font-medium text-gray-600">Nereye:</label>
        <select v-model="to" id="to" name="to" class="p-2 border border-gray-300 rounded-md">
          <option value="" disabled selected>Lütfen seçiniz</option>
          <option v-for="city in toCities" :key="city" :value="city">{{ city.Sehir }}</option>
        </select>
      </div>

      <div class="flex items-center mb-4">
        <label for="date" class="mr-2 text-sm font-medium text-gray-600">Tarih:</label>
        <input v-model="date" type="date" id="date" name="date" class="p-2 border border-gray-300 rounded-md" 
        :min="today" :disabledDates="disabledDates">
      </div>

      <div class="flex items-center">
        <button @click="searchTicket" class="bg-blue-500 text-white p-2 rounded-md">Bilet Ara</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  mounted() {
    this.fetchData();
    this.from = 'İstanbul';
    this.to = 'Ankara';
    this.date = this.today;

  },
  data() {
    return {
      today: new Date().toISOString().split('T')[0],
      from: '',
      to: '',
      date: '',
      fromCities: [],
      toCities:[],
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
    },
    searchTicket() {
      const postData = {
          from: this.from,
          to: this.to,
          date: this.date,
        };

        this.$router.push({
        name: 'seferler',
        params: postData,
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

<style scoped>
</style>
