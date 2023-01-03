interface Api {
  baseUrl: string;
}

export interface Configuration {
  api: Api;
}

export interface Environment {
  env: 'development' | 'production';
}
