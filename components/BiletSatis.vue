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
  mounted(){
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
      cities: ['İstanbul', 'Ankara', 'İzmir', 'Antalya', 'Bursa'] ,
      disabledDates: {
        to: new Date().toISOString().split('T')[0]
      },
    };
  },
  methods: {
    buyTicket() {
      console.log('Bilet Satın Alındı!');
    }
  }
};
</script>

<style scoped>
/* Bileşen özel stillemeleri burada yapılabilir */
</style>
