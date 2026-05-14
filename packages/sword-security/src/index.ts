import { registerPlugin } from '@capacitor/core';

import type { SecurityPlugin } from './definitions';

const Security = registerPlugin<SecurityPlugin>('Security', {
  web: () => import('./web').then((m) => new m.SecurityWeb()),
});

export * from './definitions';
export { Security };
