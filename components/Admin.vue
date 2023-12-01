<template>
    <div class="max-w-md mx-auto p-4">
      <div>
        <h2 class="text-xl font-bold mb-2">Sefer Bilgileri</h2>
        <form>

          <div class="mb-4">
            <label for="from" class="block text-sm font-medium text-gray-600">Kalkış Yeri:</label>
            <input type="text" id="from" v-model="from" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
         
          <div class="mb-4">
              <label for="fromTime" class="block text-sm font-medium text-gray-600">Kalkış Saati</label>
                <input type="text" id="fromTime" v-model="fromTime" class="mt-1 p-2 border border-gray-300 rounded-md w-full"/>
         </div>

          <div class="mb-4">
            <label for="to" class="block text-sm font-medium text-gray-600">Varış Yeri:</label>
            <input type="text" id="to" v-model="to" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
         
         <div class="mb-4">
              <label for="toTime" class="block text-sm font-medium text-gray-600">Varış Saati:</label>
                <input type="text" id="toTime" v-model="toTime" class="mt-1 p-2 border border-gray-300 rounded-md w-full"/>
         </div>
        
          <div class="mb-4">
            <label for="company" class="block text-sm font-medium text-gray-600">Firma:</label>
            <input type="text" id="company" v-model="company" class="mt-1 p-2 border border-gray-300 rounded-md w-full" />
          </div>
          <div class="mb-4">
            <label for="bus" class="block text-sm font-medium text-gray-600">Otobüs:</label>
            <input type="text" id="bus" v-model="bus" class="mt-1 p-2 border border-gray-300 rounded-md w-full"/>
          </div>
        </form>
        
      </div>

      <button @click="createTrip" class="bg-blue-500 text-white p-2 rounded-md w-full">Sefer Kaydı Oluştur</button>
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
        createTrip() {
            console.log("kayıt oluşturuldu!");
        },
    }
  };
  </script>
  
  <style scoped>
  </style>
  