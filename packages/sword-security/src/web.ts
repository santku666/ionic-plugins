import { WebPlugin } from '@capacitor/core';

import type { SwordSecurityPlugin } from './definitions';

export class SecurityWeb extends WebPlugin implements SwordSecurityPlugin {
  async isDeveloperModeEnabled(): Promise<{ enabled: boolean }> {
    // Web implementation - developer mode is typically not applicable
    return { enabled: false };
  }

  async isRooted(): Promise<{ rooted: boolean }> {
    // Web implementation - rooting is not applicable on web
    return { rooted: false };
  }

  async openDeveloperSettings(): Promise<void> {
    console.warn('Not supported on web');
  }
}
