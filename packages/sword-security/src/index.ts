import { registerPlugin } from '@capacitor/core';

import type { SwordSecurityPlugin } from './definitions';

const SwordSecurityPlugin = registerPlugin<SwordSecurityPlugin>('SwordSecurityPlugin', {
  web: () => import('./web').then((m) => new m.SecurityWeb()),
});

export * from './definitions';
export { SwordSecurityPlugin };
