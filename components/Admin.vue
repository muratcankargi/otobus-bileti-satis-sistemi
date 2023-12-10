<template>
  <div class="max-w-md mx-auto p-4">
    <div>
      <h2 class="text-xl font-bold mb-2">Sefer Bilgileri</h2>
      <form>

        <div class="mb-4">
          <label for="from" class="block text-sm font-medium text-gray-600">Kalkış Yeri:</label>
          <input type="text" id="from" v-model="from" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
        </div>

        <div>
          <label for="datepicker" class="block text-l font-medium text-gray-600">Tarih ve Saat Seçimi:</label>
          <flat-pickr v-model="fromDateTime" :config="flatpickrConfig"
            class="mt-1 p-2 border border-gray-300 rounded-md w-full"></flat-pickr>
        </div>

        <div class="mb-4">
          <label for="to" class="block text-sm font-medium text-gray-600">Varış Yeri:</label>
          <input type="text" id="to" v-model="to" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
        </div>

        <div>
          <label for="datepicker" class="block text-l font-medium text-gray-600">Tarih ve Saat Seçimi:</label>
          <flat-pickr v-model="toDateTime" :config="flatpickrConfig"
            class="mt-1 p-2 border border-gray-300 rounded-md w-full"></flat-pickr>
        </div>

        <div class="mb-4">
          <label for="to" class="block text-l font-medium text-gray-600">Firma:</label>
          <select v-model="company" id="company" name="company" @change="getBus(company)"
            class="mt-1 p-2 border border-gray-300 rounded-md w-full">
            <option value="" disabled selected>Lütfen seçiniz</option>
            <option v-for="company in companiesNames" :key="company" :value="company">{{ company }}</option>
          </select>
        </div>
        <div class="mb-4">
          <label for="bus" class="block text-l font-medium text-gray-600">Otobüs:</label>
          <select v-model="bus" id="bus" name="bus" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
            <option value="" disabled selected>Lütfen seçiniz</option>
            <option v-for="bus in busses" :key="company" :value="bus">{{ bus }}</option>
          </select>
        </div>
      </form>
      <button @click="createTrip" class="bg-blue-500 text-white p-2 rounded-md w-full">Sefer Kaydı Oluştur</button>

    </div>
  </div>
  <transition name="fade">
    <div v-if="seferKaydiOlusturuldu" class="fixed bottom-5 right-5 bg-green-500 text-white p-4 rounded shadow">
      Sefer kaydı başarıyla oluşturuldu!
    </div>
  </transition>
</template>
  
<script>
import axios from 'axios';
import flatPickr from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";
export default {

  mounted() {
    this.fetchData();
  },

  components: {
    flatPickr,
  },
  data() {
    return {
      seferKaydiOlusturuldu: false,
      fromDateTime: "",
      toDateTime: "",
      selectedDateTime: null,
      flatpickrConfig: {
        enableTime: true,
        dateFormat: "Y-m-d H:i",
      },
      busses: [],
      companies: [],
      companiesNames: [],
      from: "",
      fromTime: "",
      to: "",
      toTime: "",
      company: "",
      bus: "",
      selectedGender: '',
      firmaId: "",
      bussRequest: [],
    };
  },
  methods: {
    async fetchData() {
      this.companies = await axios.get("http://localhost:8080/api/firmalar");
      this.companiesNames = this.companies.data.map((firma) => firma.firmaAdi);
    },
    async getBus(companyName) {
      const foundFirma = this.companies.data.find((firma) => firma.firmaAdi === companyName);
      this.firmaId = foundFirma.id;
      this.bussRequest = await axios.post("http://localhost:8080/api/otobusler/getOtobusBySeferId/" + foundFirma.id);
      this.busses = this.bussRequest.data.map((bus) => bus.otobusPlaka);
    },
    async createTrip() {
      const otobusId = this.bussRequest.data.find((bus) => bus.otobusPlaka === this.bus).id;
      const seferData = {
        "firmaId": this.firmaId,
        "otobusId": otobusId,
        "seferKalkisYeri": this.from,
        "seferVarisYeri": this.to,
        "seferKalkisSaati": new Date(this.fromDateTime).toISOString(),
        "seferVarisSaati": new Date(this.toDateTime).toISOString(),

      }

      await axios.post("http://localhost:8080/api/seferler", seferData)
        .then(request => {
          this.seferKaydiOlusturuldu = true;
          setTimeout(() => {
            this.seferKaydiOlusturuldu = false;
          }, 2000);
        })
        .catch(error =>
          console.error("sefer kaydı oluşturulamadı!"));
    },

  },

};
</script>
  
<style scoped></style>
  