<template>
  <div class="flex items-center justify-center h-screen">
    <div class="w-full max-w-md p-4">
      <h1 class="text-2xl font-bold mb-4">Şehirler Arası Otobüs Bilet Sistemi</h1>
      
      <div class="mb-4">
        <label for="from" class="block text-sm font-medium text-gray-600">Nereden:</label>
        <select v-model="from" id="from" name="from" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
          <option value="" disabled selected>Lütfen seçiniz</option>
          <option v-for="city in cities" :key="city" :value="city">{{ city }}</option>
        </select>
      </div>

      <div class="mb-4 flex justify-center">
        <button @click="switchCities" class="bg-blue-500 text-white p-2 rounded-md">
          <svg fill="#131717" width="20" height="20" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M6.99 11L3 15l3.99 4v-3H14v-2H6.99v-3zM21 9l-3.99-4v3H10v2h7.01v3L21 9z"></path></svg>
        </button>
      </div>

      <div class="mb-4">
        <label for="to" class="block text-sm font-medium text-gray-600">Nereye:</label>
        <select v-model="to" id="to" name="to" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
          <option value="" disabled selected>Lütfen seçiniz</option>
          <option v-for="city in cities" :key="city" :value="city">{{ city }}</option>
        </select>
      </div>

      <div class="mb-4">
        <label for="date" class="block text-sm font-medium text-gray-600">Tarih:</label>
        <input v-model="date" type="date" id="date" name="date" class="mt-1 p-2 border border-gray-300 rounded-md w-full" :min="today" :disabledDates="disabledDates">
      </div>

      <button @click="buyTicket" class="bg-blue-500 text-white p-2 rounded-md w-full">Bilet Satın Al</button>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
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
      cities: ['İstanbul', 'Ankara', 'İzmir', 'Antalya', 'Bursa'],
      disabledDates: {
        to: new Date().toISOString().split('T')[0]
      }
    };
  },
  methods: {
    buyTicket() {
      console.log('Bilet Satın Alındı!');
      this.$router.push('/seferler');
    },
    switchCities() {
      const temp = this.from;
      this.from = this.to;
      this.to = temp;
    }
  }
};
</script>

<style scoped>
</style>
