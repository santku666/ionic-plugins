import { WebPlugin } from '@capacitor/core';

import type { SecurityPlugin } from './definitions';

export class SecurityWeb extends WebPlugin implements SecurityPlugin {
  async isDeveloperModeEnabled(): Promise<{ enabled: boolean }> {
    // Web implementation - developer mode is typically not applicable
    return { enabled: false };
  }

  async isRooted(): Promise<{ rooted: boolean }> {
    // Web implementation - rooting is not applicable on web
    return { rooted: false };
  }
}
