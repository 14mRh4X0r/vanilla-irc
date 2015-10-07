/*
 * Copyright (C) 2010-2015 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014-2015 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler; // VanillaIRC
import com.minecraftonline.vanillairc.ObfuscationHelper; // VanillaIRC
import com.minecraftonline.vanillairc.VanillaIRC; // VanillaIRC
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class mk {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lq> i = Lists.newArrayList();
    private final Map<UUID, lq> j = Maps.newHashMap();
    private final mp k;
    private final mh l;
    private final ml m;
    private final mr n;
    private final Map<UUID, ni> o;
    private ayv p;
    private boolean q;
    protected int e;
    private int r;
    private ahf.a s; // VanillaIRC -- faulty decompilation: ahf -> ahf.a
    private boolean t;
    private int u;

    { ObfuscationHelper.setLoginManager(this, mk.class); } // VanillaIRC -- store static reference to object

    public mk(MinecraftServer var1) {
        this.k = new mp(a);
        this.l = new mh(b);
        this.m = new ml(c);
        this.n = new mr(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(ek var1, lq var2) {
        GameProfile var3 = var2.cz();
        mg var4 = this.h.aA();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dn var7 = this.a(var2);

        var2.a((ahc) this.h.a(var2.ai));
        var2.c.a((lo) var2.k);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info(var2.h_() + "[" + var8 + "] logged in with entity id " + var2.I() + " at (" + var2.o + ", " + var2.p + ", " + var2.q + ")");
        lo var9 = this.h.a(var2.ai);
        aym var10 = var9.T();
        cj var11 = var9.R();

        this.a(var2, (lq) null, var9);
        lz var12 = new lz(this.h, var1, var2);

        var12.a((ff) (new gt(var2.I(), var2.c.b(), var10.s(), var9.s.p().a(), var9.ae(), this.p(), var10.t(), var9.U().b("reducedDebugInfo"))));
        var12.a((ff) (new gh("MC|Brand", (new em(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((ff) (new fw(var10.x(), var10.y())));
        var12.a((ff) (new hw(var11)));
        var12.a((ff) (new gy(var2.bF)));
        var12.a((ff) (new hk(var2.bn.d)));
        this.f(var2);
        var2.D().d();
        var2.D().b(var2);
        this.a((kv) var9.ad(), var2);
        this.h.aC();
        fb var13;

        if (!var2.h_().equalsIgnoreCase(var6)) {
            var13 = new fb("multiplayer.player.joined.renamed", new Object[] { var2.i_(), var6});
        } else {
            var13 = new fb("multiplayer.player.joined", new Object[] { var2.i_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((eu) var13);
        this.c(var2);
        var12.a(var2.o, var2.p, var2.q, var2.u, var2.v);
        this.b(var2, var9);
        if (!this.h.X().isEmpty()) {
            var2.a(this.h.X(), this.h.Y());
        }

        Iterator var14 = var2.bE().iterator();

        while (var14.hasNext()) {
            rb var15 = (rb) var14.next();

            var12.a((ff) (new ie(var2.I(), var15)));
        }

        if (var7 != null) {
            if (var7.b("RootVehicle", 10)) {
                dn var19 = var7.o("RootVehicle");
                rh var21 = arx.a(var19.o("Entity"), var9, true);

                if (var21 != null) {
                    UUID var16 = var19.a("Attach");
                    Iterator var17;
                    rh var18;

                    if (var21.aU().equals(var16)) {
                        var2.a(var21, true);
                    } else {
                        var17 = var21.bm().iterator();

                        while (var17.hasNext()) {
                            var18 = (rh) var17.next();
                            if (var18.aU().equals(var16)) {
                                var2.a(var18, true);
                                break;
                            }
                        }
                    }

                    if (!var2.aC()) {
                        f.warn("Couldn\'t reattach entity to player");
                        var9.f(var21);
                        var17 = var21.bm().iterator();

                        while (var17.hasNext()) {
                            var18 = (rh) var17.next();
                            var9.f(var18);
                        }
                    }
                }
            } else if (var7.b("Riding", 10)) {
                rh var20 = arx.a(var7.o("Riding"), var9, true);

                if (var20 != null) {
                    var2.a(var20, true);
                }
            }
        }

        var2.j_();
    }

    protected void a(kv var1, lq var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            azi var5 = (azi) var4.next();

            var2.a.a((ff) (new hu(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            azh var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    ff var8 = (ff) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(lo[] var1) {
        this.p = var1[0].S().e();
        var1[0].aj().a(new aqy() {
            public void a(ara var1, double var2) {
                mk.this.a((ff) (new hi(var1, hi.a.a))); // VanillaIRC -- faulty decompilation: hi -> hi.a
            }

            public void a(ara var1, double var2, double var4, long var6) {
                mk.this.a((ff) (new hi(var1, hi.a.b))); // VanillaIRC -- faulty decompilation: hi -> hi.a
            }

            public void a(ara var1, double var2, double var4) {
                mk.this.a((ff) (new hi(var1, hi.a.c))); // VanillaIRC -- faulty decompilation: hi -> hi.a
            }

            public void a(ara var1, int var2) {
                mk.this.a((ff) (new hi(var1, hi.a.e))); // VanillaIRC -- faulty decompilation: hi -> hi.a
            }

            public void b(ara var1, int var2) {
                mk.this.a((ff) (new hi(var1, hi.a.f))); // VanillaIRC -- faulty decompilation: hi -> hi.a
            }

            public void b(ara var1, double var2) {}

            public void c(ara var1, double var2) {}
        });
    }

    public void a(lq var1, lo var2) {
        lo var3 = var1.x();

        if (var2 != null) {
            var2.w().b(var1);
        }

        var3.w().a(var1);
        var3.r().d((int) var1.o >> 4, (int) var1.q >> 4);
    }

    public int d() {
        return lu.b(this.s());
    }

    public dn a(lq var1) {
        dn var2 = this.h.d[0].T().h();
        dn var3;

        if (var1.h_().equals(this.h.Q()) && var2 != null) {
            var3 = this.h.aI().a((os) ot.b, var2);
            var1.f(var3);
            f.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(lq var1) {
        this.p.a(var1);
        ni var2 = (ni) this.o.get(var1.aU());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(lq var1) {
        this.i.add(var1);
        this.j.put(var1.aU(), var1);
        this.a((ff) (new ha(ha.a.a, new lq[] { var1}))); // VanillaIRC -- faulty decompilation: ha -> ha.a
        lo var2 = this.h.a(var1.ai);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lq var4 = (lq) this.i.get(var3);

            var1.a.a((ff) (new ha(ha.a.a, new lq[] { var4}))); // VanillaIRC -- faulty decompilation: ha -> ha.a
        }

        var2.a((rh) var1);
        this.a(var1, (lo) null);
    }

    public void d(lq var1) {
        var1.x().w().c(var1);
    }

    public void e(lq var1) {
        lo var2 = var1.x();

        var1.b(nn.f);
        this.b(var1);
        if (var1.aC()) {
            rh var3 = var1.bn();

            if (var3.b(lq.class).size() == 1) {
                f.debug("Removing player mount");
                var1.p();
                var2.f(var3);
                Iterator var4 = var3.bm().iterator();

                while (var4.hasNext()) {
                    rh var5 = (rh) var4.next();

                    var2.f(var5);
                }

                var2.a(var1.aa, var1.ac).e();
            }
        }

        var2.e(var1);
        var2.w().b(var1);
        this.i.remove(var1);
        UUID var6 = var1.aU();
        lq var7 = (lq) this.j.get(var6);

        if (var7 == var1) {
            this.j.remove(var6);
            this.o.remove(var6);
        }

        this.a((ff) (new ha(ha.a.e, new lq[] { var1}))); // VanillaIRC -- faulty decompilation: ha -> ha.a
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            mq var5 = (mq) ((mo) this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to mo

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            mi var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var3.c());
            }

            return var4;
        } else {
            return this.i.size() >= this.e && !this.f(var2) ? "The server is full!" : null;
        }
    }

    public lq g(GameProfile var1) {
        UUID var2 = zh.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            lq var5 = (lq) this.i.get(var4);

            if (var5.aU().equals(var2)) {
                var3.add(var5);
            }
        }

        lq var7 = (lq) this.j.get(var1.getId());

        if (var7 != null && !var3.contains(var7)) {
            var3.add(var7);
        }

        Iterator var8 = var3.iterator();

        while (var8.hasNext()) {
            lq var6 = (lq) var8.next();

            var6.a.c("You logged in from another location");
        }

        Object var9;

        if (this.h.V()) {
            var9 = new lj(this.h.a(0));
        } else {
            var9 = new lr(this.h.a(0));
        }

        return new lq(this.h, this.h.a(0), var1, (lr) var9);
    }

    public lq a(lq var1, int var2, boolean var3) {
        var1.x().v().b(var1);
        var1.x().v().b((rh) var1);
        var1.x().w().b(var1);
        this.i.remove(var1);
        this.h.a(var1.ai).f(var1);
        cj var4 = var1.cD();
        boolean var5 = var1.cE();

        var1.ai = var2;
        Object var6;

        if (this.h.V()) {
            var6 = new lj(this.h.a(var1.ai));
        } else {
            var6 = new lr(this.h.a(var1.ai));
        }

        lq var7 = new lq(this.h, this.h.a(var1.ai), var1.cz(), (lr) var6);

        var7.a = var1.a;
        var7.a((zh) var1, var3);
        var7.d(var1.I());
        var7.v(var1);
        var7.a(var1.ci());
        Iterator var8 = var1.J().iterator();

        while (var8.hasNext()) {
            String var9 = (String) var8.next();

            var7.a(var9);
        }

        lo var10 = this.h.a(var1.ai);

        this.a(var7, var1, var10);
        cj var11;

        if (var4 != null) {
            var11 = zh.a(this.h.a(var1.ai), var4, var5);
            if (var11 != null) {
                var7.b((double) ((float) var11.p() + 0.5F), (double) ((float) var11.q() + 0.1F), (double) ((float) var11.r() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((ff) (new gn(0, 0.0F)));
            }
        }

        var10.r().d((int) var7.o >> 4, (int) var7.q >> 4);

        while (!var10.a((rh) var7, var7.bd()).isEmpty() && var7.p < 256.0D) {
            var7.b(var7.o, var7.p + 1.0D, var7.q);
        }

        var7.a.a((ff) (new hg(var7.ai, var7.k.ae(), var7.k.T().t(), var7.c.b())));
        var11 = var10.R();
        var7.a.a(var7.o, var7.p, var7.q, var7.u, var7.v);
        var7.a.a((ff) (new hw(var11)));
        var7.a.a((ff) (new hq(var7.bI, var7.bH, var7.bG)));
        this.b(var7, var10);
        this.f(var7);
        var10.w().a(var7);
        var10.a((rh) var7);
        this.i.add(var7);
        this.j.put(var7.aU(), var7);
        var7.j_();
        var7.c(var7.bG());
        return var7;
    }

    public void f(lq var1) {
        GameProfile var2 = var1.cz();
        int var3 = this.h(var2) ? this.m.a(var2) : 0;

        var3 = this.h.R() && this.h.d[0].T().u() ? 4 : var3;
        var3 = this.t ? 4 : var3;
        this.a(var1, var3);
    }

    public void a(lq var1, int var2, cj var3) {
        int var4 = var1.ai;
        lo var5 = this.h.a(var1.ai);

        var1.ai = var2;
        lo var6 = this.h.a(var1.ai);

        var1.a.a((ff) (new hg(var1.ai, var1.k.ae(), var1.k.T().t(), var1.c.b())));
        this.f(var1);
        var5.f(var1);
        var1.E = false;
        if (var3 == null) {
            this.a(var1, var4, var5, var6);
        } else if (var1.ao()) {
            var5.a((rh) var1, false);
            var1.a(var3, var1.u, var1.v);
            var6.a((rh) var1);
            var6.a((rh) var1, false);
            var1.a((ahc) var6);
        }

        this.a(var1, var5);
        var1.a.a(var1.o, var1.p, var1.q, var1.u, var1.v);
        var1.c.a(var6);
        this.b(var1, var6);
        this.g(var1);
        Iterator var7 = var1.bE().iterator();

        while (var7.hasNext()) {
            rb var8 = (rb) var7.next();

            var1.a.a((ff) (new ie(var1.I(), var8)));
        }

    }

    public void a(rh var1, int var2, lo var3, lo var4) {
        double var5 = var1.o;
        double var7 = var1.q;
        double var9 = 8.0D;
        float var11 = var1.u;

        var3.B.a("moving");
        if (var1.ai == -1) {
            var5 = oh.a(var5 / var9, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = oh.a(var7 / var9, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.p, var7, var1.u, var1.v);
            if (var1.ao()) {
                var3.a(var1, false);
            }
        } else if (var1.ai == 0) {
            var5 = oh.a(var5 * var9, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = oh.a(var7 * var9, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.p, var7, var1.u, var1.v);
            if (var1.ao()) {
                var3.a(var1, false);
            }
        } else {
            cj var12;

            if (var2 == 1) {
                var12 = var4.R();
            } else {
                var12 = var4.p();
            }

            var5 = (double) var12.p();
            var1.p = (double) var12.q();
            var7 = (double) var12.r();
            var1.b(var5, var1.p, var7, 90.0F, 0.0F);
            if (var1.ao()) {
                var3.a(var1, false);
            }
        }

        var3.B.b();
        if (var2 != 1) {
            var3.B.a("placing");
            var5 = (double) oh.a((int) var5, -29999872, 29999872);
            var7 = (double) oh.a((int) var7, -29999872, 29999872);
            if (var1.ao()) {
                var1.b(var5, var1.p, var7, var1.u, var1.v);
                var4.x().a(var1, var11);
                var4.a(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((ahc) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((ff) (new ha(ha.a.c, this.i))); // VanillaIRC -- faulty decompilation: ha -> ha.a
            this.u = 0;
        }

    }

    public void a(ff var1) {
        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            ((lq) this.i.get(var2)).a.a(var1);
        }

    }

    public void a(ff var1, int var2) {
        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lq var4 = (lq) this.i.get(var3);

            if (var4.ai == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(zh var1, eu var2) {
        azn var3 = var1.aI();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                lq var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(zh var1, eu var2) {
        azn var3 = var1.aI();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                lq var5 = (lq) this.i.get(var4);

                if (var5.aI() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String b(boolean var1) {
        String var2 = "";
        ArrayList var3 = Lists.newArrayList(this.i);

        for (int var4 = 0; var4 < var3.size(); ++var4) {
            if (var4 > 0) {
                var2 = var2 + ", ";
            }

            var2 = var2 + ((lq) var3.get(var4)).h_();
            if (var1) {
                var2 = var2 + " (" + ((lq) var3.get(var4)).aU().toString() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lq) this.i.get(var2)).h_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lq) this.i.get(var2)).cz();
        }

        return var1;
    }

    public mp h() {
        return this.k;
    }

    public mh i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        int var2 = this.h.q();

        ((mo) this.m).a((mn) (new mm(var1, this.h.q(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to mo
        this.a(this.a(var1.getId()), var2);
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
        this.a(this.a(var1.getId()), 0);
    }

    private void a(lq var1, int var2) {
        if (var1 != null && var1.a != null) {
            byte var3;

            if (var2 <= 0) {
                var3 = 24;
            } else if (var2 >= 4) {
                var3 = 28;
            } else {
                var3 = (byte) (24 + var2);
            }

            var1.a.a((ff) (new gj(var1, var3)));
        }

    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.R() && this.h.d[0].T().u() && this.h.Q().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public lq a(String var1) {
        Iterator var2 = this.i.iterator();

        lq var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (lq) var2.next();
        } while (!var3.h_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, ff var10) {
        this.a((zh) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(zh var1, double var2, double var4, double var6, double var8, int var10, ff var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            lq var13 = (lq) this.i.get(var12);

            if (var13 != var1 && var13.ai == var10) {
                double var14 = var2 - var13.o;
                double var16 = var4 - var13.p;
                double var18 = var6 - var13.q;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void j() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            this.b((lq) this.i.get(var1));
        }

    }

    public void d(GameProfile var1) {
        ((mo) this.n).a((mn) (new ms(var1))); // VanillaIRC -- obfuscation anomaly: cast this.m to mo
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public mr k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public ml m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(lq var1, lo var2) {
        ara var3 = this.h.d[0].aj();

        var1.a.a((ff) (new hi(var3, hi.a.d))); // VanillaIRC -- faulty decompilation: hi -> hi.a
        var1.a.a((ff) (new hx(var2.P(), var2.Q(), var2.U().b("doDaylightCycle"))));
        if (var2.W()) {
            var1.a.a((ff) (new gn(1, 0.0F)));
            var1.a.a((ff) (new gn(7, var2.j(1.0F))));
            var1.a.a((ff) (new gn(8, var2.h(1.0F))));
        }

    }

    public void g(lq var1) {
        var1.a(var1.bo);
        var1.u();
        var1.a.a((ff) (new hk(var1.bn.d)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].S().e().f();
    }

    public boolean r() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List<lq> b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.i.iterator();

        while (var3.hasNext()) {
            lq var4 = (lq) var3.next();

            if (var4.z().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int s() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.h;
    }

    public dn t() {
        return null;
    }

    private void a(lq var1, lq var2, ahc var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.T().q());
    }

    public void u() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            ((lq) this.i.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(eu var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);
        
        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }
    
    public void sendMessageToPlayers(eu var1, boolean var2) {
        this.h.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((ff) (new fy(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(eu.a.a(json), false);
    } // VanillaIRC end

    public void a(eu var1) {
        this.a(var1, true);
    }

    public ni a(zh var1) {
        UUID var2 = var1.aU();
        ni var3 = var2 == null ? null : (ni) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).S().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.h_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new ni(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.d != null) {
            lo[] var2 = this.h.d;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                lo var5 = var2[var4];

                if (var5 != null) {
                    var5.w().a(var1);
                    var5.v().a(var1);
                }
            }

        }
    }

    public List<lq> v() {
        return this.i;
    }

    public lq a(UUID var1) {
        return (lq) this.j.get(var1);
    }

    public boolean f(GameProfile var1) {
        return false;
    }

}
