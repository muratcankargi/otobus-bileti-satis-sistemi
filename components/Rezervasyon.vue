<template>
    <div class="max-w-md mx-auto p-4">
      <div>
        <h2 class="text-xl font-bold mb-2">Bilet Bilgileri</h2>
        <form>
            <label for="gender" class="block text-sm font-medium text-gray-600">Cinsiyet:</label>
    <select v-model="selectedGender" id="gender" name="gender" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
        <option value="" disabled selected>Lütfen seçiniz</option>
        <option value="male">Erkek</option>
      <option value="female">Kadın</option>
    </select>

          <div class="mb-4">
            <label for="firstName" class="block text-sm font-medium text-gray-600">Ad:</label>
            <input type="text" id="firstName" v-model="ticketInfo.firstName" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
          <div class="mb-4">
            <label for="lastName" class="block text-sm font-medium text-gray-600">Soyad:</label>
            <input type="text" id="lastName" v-model="ticketInfo.lastName" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
         <div class="mb-4">
              <label for="tcNumber" class="block text-sm font-medium text-gray-600">TC Kimlik No:</label>
                <input type="text" id="tcNumber" v-model="ticketInfo.tcNumber" class="mt-1 p-2 border border-gray-300 rounded-md w-full" 
                    inputmode="numeric" @input="validateTCNumber"/>
         </div>
        </form>
      </div>
  
      <div class="mt-8">
        <h2 class="text-xl font-bold mb-2">İletişim Bilgileri</h2>
        <form>
          <div class="mb-4">
            <label for="email" class="block text-sm font-medium text-gray-600">E-posta:</label>
            <input type="email" id="email" v-model="contactInfo.email" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
          <div class="mb-4">
            <label for="phoneNumber" class="block text-sm font-medium text-gray-600">Telefon Numarası:</label>
            <input type="tel" id="phoneNumber" v-model="contactInfo.phoneNumber" class="mt-1 p-2 border border-gray-300 rounded-md w-full"
                placeholder="(5xx) xxx xx xx" @input="formatPhoneNumber"/>
          </div>
        </form>


        
      </div>

      <button @click="buyTicket" class="bg-blue-500 text-white p-2 rounded-md w-full">Bilet Satın Al</button>
    </div>
    

  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedGender : '',
        ticketInfo: {
          firstName: '',
          lastName: '',
          tcNumber: ''
        },
        contactInfo: {
          email: '',
          phoneNumber: ''
        }
      };
    },
    methods : {
        validateTCNumber() {
            this.ticketInfo.tcNumber = this.ticketInfo.tcNumber.replace(/\D/g, ''); // just numeric input

            if (this.ticketInfo.tcNumber.length > 11) {
                this.ticketInfo.tcNumber = this.ticketInfo.tcNumber.slice(0, 11); // max lenght 11
            }
        },
        formatPhoneNumber() {
                const enteredValue = this.contactInfo.phoneNumber.replace(/\D/g, ''); // Sayısal olmayan karakterleri temizle
                const formattedValue = enteredValue.replace(/(\d{1})(\d{3})(\d{3})(\d{2})(\d{2})/, '($1$2) $3 $4 $5');
                this.ticketInfo.tcNumber = this.ticketInfo.tcNumber.replace(/\D/g, ''); // just numeric input

                this.contactInfo.phoneNumber = formattedValue.slice(0, 15);
        },
        buyTicket(){
            console.log("Bilet Rezervasyonu yapıldı!");
        }

    }
  };
  </script>
  
  <style scoped>
  </style>
  