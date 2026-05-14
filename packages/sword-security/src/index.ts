import { registerPlugin } from '@capacitor/core';

import type { SecurityPlugin } from './definitions';

const SecurityPlugin = registerPlugin<SecurityPlugin>('SecurityPlugin', {
  web: () => import('./web').then((m) => new m.SecurityWeb()),
});

export * from './definitions';
export { SecurityPlugin };
