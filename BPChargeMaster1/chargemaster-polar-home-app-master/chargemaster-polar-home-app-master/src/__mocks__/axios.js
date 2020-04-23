const mockAxios = {
  create: jest.fn().mockImplementation(() => {
    return mockAxios
  }),
  get: jest.fn().mockImplementation(() => {
    return new Promise( (res, rej) => {
      mockAxios._resolve = res;
      mockAxios._reject = rej;
    });
  }),
  post: jest.fn().mockImplementation(() => {
    return new Promise( (res, rej) => {
      mockAxios._resolve = res;
      mockAxios._reject = rej;
    });
  }),
  mockSuccess (resp) {
    this._resolve(resp);
  },
  mockError (resp) {
    this._reject(resp);
  },
  _resolve: null,
  _reject: null,
  defaults: {
    baseURL: "ABaseUrl",
    headers: {
      common: []
    },
    timeout: 1
  },
  reset()
  {
    this._resolve = null
    this._reject = null
    this.defaults = {
      baseURL: "ABaseUrl",
      headers: {
        common: []
      }
    }
  }
};

export default mockAxios;