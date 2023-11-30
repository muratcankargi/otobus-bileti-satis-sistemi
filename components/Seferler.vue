<template>
    <div class="relative overflow-x-auto shadow-md">
      <table class="w-full text-sm text-left">
        <thead class="text-xs uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" class="px-6 py-3">Firma</th>
            <th scope="col" class="px-6 py-3">Nereden</th>
            <th scope="col" class="px-6 py-3">Nereye</th>
            <th scope="col" class="px-6 py-3">Sefer Tarihi</th>
            <th scope="col" class="px-6 py-3"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(data, index) in dataTable" :key="index" class="border-b dark:border-gray-700 hover:bg-gray-100">
            <td scope="row" class="px-6 py-4">{{ data.firma }}</td>
            <td class="px-6 py-4">{{ data.from }}</td>
            <td class="px-6 py-4">{{ data.to }}</td>
            <td class="px-6 py-4">{{data.date }}</td>
            <td class="px-6 py-4 text-right">
              <router-link class="font-medium text-blue-600 dark:text-blue-500 hover:underline" :to="{ path: '/rezervasyon'}">Bilet Al</router-link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  export default {
    mounted() {
     },
    data() {
      return {
        dataTable: [
          { firma: 'Zafer Turizm', from: 'İstanbul', to: "Suriye", date: '2023-11-28T12:34:56'},
          { firma: 'Lüks Artvin', from: 'Ankara', to: "İzmir", date: '2023-11-27T09:45:23'},
        ],
        input_data: "",
      };
    },
    methods: {
    },
    
  };
  </script>
  
  <style scoped>
  
  </style>
  