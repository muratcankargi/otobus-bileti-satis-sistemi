<template>
  <div class="max-w-md mx-auto p-4">
    <div class="flex space-x-4">
      <div class="flex-1">
        <h2 class="text-xl font-bold mb-2">Kişisel Bilgiler</h2>
        <form>
          <label for="gender" class="block text-sm font-medium text-gray-600">Cinsiyet:</label>
          <select v-model="selectedGender" id="gender" name="gender"
            class="mt-1 p-2 border border-gray-300 rounded-md w-full">
            <option value="" disabled selected>Lütfen seçiniz</option>
            <option value="Erkek">Erkek</option>
            <option value="Kadın">Kadın</option>
          </select>

          <div class="mb-4">
            <label for="firstName" class="block text-sm font-medium text-gray-600">Ad:</label>
            <input type="text" id="firstName" v-model="ticketInfo.firstName"
              class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
          <div class="mb-4">
            <label for="lastName" class="block text-sm font-medium text-gray-600">Soyad:</label>
            <input type="text" id="lastName" v-model="ticketInfo.lastName"
              class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
          <div class="mb-4">
            <label for="tcNumber" class="block text-sm font-medium text-gray-600">TC Kimlik No:</label>
            <input type="text" id="tcNumber" v-model="ticketInfo.tcNumber"
              class="mt-1 p-2 border border-gray-300 rounded-md w-full" inputmode="numeric" @input="validateTCNumber" />
          </div>
        </form>
      </div>

      <div class="flex-1">
        <h2 class="text-xl font-bold mb-2">İletişim Bilgileri</h2>
        <form>
          <div class="mb-4">
            <label for="email" class="block text-sm font-medium text-gray-600">E-posta:</label>
            <input type="email" id="email" v-model="contactInfo.email"
              class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
          <div class="mb-4">
            <label for="phoneNumber" class="block text-sm font-medium text-gray-600">Telefon Numarası:</label>
            <input type="tel" id="phoneNumber" v-model="contactInfo.phoneNumber"
              class="mt-1 p-2 border border-gray-300 rounded-md w-full" placeholder="(5xx) xxx xx xx"
              @input="formatPhoneNumber" />
          </div>
        </form>



      </div>
    </div>
    <div class="mt-8">
      <h2 class="text-xl font-bold mb-2">Firma </h2>
      <p class="text-lg font-semibold text-gray-800">{{ this.searchData.firma }}</p>
      <h2 class="text-xl font-bold mb-2">Otobüs Kalkış Yeri ve Tarihi </h2>
      <p class="text-lg font-semibold text-gray-800">{{ this.searchData.nereden + " / " +
        formatDate(this.searchData.seferKalkisTarihi) }}</p>
      <h2 class="text-xl font-bold mb-2">Otobüs Varış Yeri ve Tarihi</h2>
      <p class="text-lg font-semibold text-gray-800">{{ this.searchData.nereye + " / " +
        formatDate(this.searchData.seferVarisTarihi) }}</p>
        <h2 class="text-xl font-bold mb-2">Sefer No: {{ this.searchData.seferNo }} Koltuk No: {{ this.searchData.koltukNo }}
      </h2>
      <h2 class="text-xl font-bold mb-2">Bilet Fiyat : {{ this.searchData.biletFiyat }}₺</h2>

      <button @click="buyTicket" class="bg-blue-500 text-white p-2 rounded-md w-full">Bilet Satın Al</button>
    </div>

  </div>
  <transition name="fade">
    <div v-if="successfullBiletBuy" class="fixed bottom-5 right-5 bg-green-500 text-white p-4 rounded shadow">
      Bilet satın alındı!
    </div>
  </transition>
</template>

<script>
import axios from 'axios';

export default {
  mounted() {
    this.searchData = this.$route.query;
  },
  data() {
    return {
      selectedGender: '',
      ticketInfo: {
        firstName: '',
        lastName: '',
        tcNumber: '',
      },
      searchData: {
        firma: null,
        nereden: "",
        nereye: "",
        seferTarihi: "",
        biletFiyat: ""
      },
      contactInfo: {
        email: '',
        phoneNumber: '',
      },
      successfullBiletBuy: false,
    };
  },
  methods: {
    validateTCNumber() {
      this.ticketInfo.tcNumber = this.ticketInfo.tcNumber.replace(/\D/g, ''); // just numeric input

      if (this.ticketInfo.tcNumber.length > 11) {
        this.ticketInfo.tcNumber = this.ticketInfo.tcNumber.slice(0, 11); // max length 11
      }
    },
    formatPhoneNumber() {
      const enteredValue = this.contactInfo.phoneNumber.replace(/\D/g, ''); // Remove non-numeric characters
      const formattedValue = enteredValue.replace(/(\d{1})(\d{3})(\d{3})(\d{2})(\d{2})/, '($1$2) $3 $4 $5');
      this.contactInfo.phoneNumber = formattedValue.slice(0, 15);
    },
    formatDate(dateTimeString) {
      const options = { day: 'numeric', month: 'long', hour: 'numeric', minute: 'numeric' };
      const formattedDate = new Date(dateTimeString).toLocaleDateString('tr-TR', options);
      return formattedDate;
    },

    async buyTicket() {
      const yolcu = {
        "ad": this.ticketInfo.firstName,
        "soyad": this.ticketInfo.lastName,
        "tckimlikNo": this.ticketInfo.tcNumber,
        "email": this.contactInfo.email,
        "cinsiyet": this.selectedGender,
      };
      await axios.post("http://localhost:8080/api/yolcular", yolcu)
        .then(request => {
          console.log("Kaydedildi");
        }).catch(error => {
          console.error("Kaydedilemedi");
        });
      const getYolcuId = await axios.post("http://localhost:8080/api/yolcular/getYolcuByEmail",
        { "email": this.contactInfo.email })

      const yolcuId = getYolcuId.data[0].id;
      const seferNo = this.searchData.seferNo;
      const koltukNo = this.searchData.koltukNo;
      const biletData = {
        "yolcuId": yolcuId,
        "seferId": seferNo,
        "koltukNo": koltukNo,
        "biletFiyat": this.searchData.biletFiyat,
        "satisTarihi": new Date().toISOString().split('T')[0],
      }

      await axios.post("http://localhost:8080/api/biletler", biletData)
        .then(request => {
          this.successfullBiletBuy = true;
          setTimeout(() => {
            this.successfullBiletBuy = false;
          }, 2000);
        })
        .catch(error => {
          console.error("Bilet Kaydedilemedi!");
        });

    },
  },
};
</script>

<style scoped></style>
