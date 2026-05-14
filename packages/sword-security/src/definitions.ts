export interface SwordSecurityPlugin {
  isDeveloperModeEnabled(): Promise<{ enabled: boolean }>;
  isRooted(): Promise<{ rooted: boolean }>;
  openDeveloperSettings(): Promise<void>;
}
