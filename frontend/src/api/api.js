import axios from 'axios'

let baseURL = '';

export const getHolidays = params => {
  var url = baseURL +'/api/v1/nationalholidays/'+ params.nation;
  if (params.year) {
    url = url +'/'+ params.year;
  }
  return axios.get(url);
};
